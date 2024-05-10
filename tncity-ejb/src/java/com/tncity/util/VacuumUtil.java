/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import com.tncity.properties.Propiedad;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.Table;

/**
 *
 * @author inmoticamaster
 */
public class VacuumUtil {

    private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);

        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static String getVacummScript() {
        StringBuilder script = new StringBuilder();
        try {
            Class[] arrC = getClasses("com.sobre5.jpa.pojo");
            for (Class c : arrC) {
                Table t = (Table) c.getAnnotation(Table.class);
                if (t != null) {
                    //script.append("VACUUM VERBOSE ANALYZE " + t.name() + ";\n");
                    //En java funciona mejor sin VERBOSE
                    script.append("VACUUM ANALYZE ").append(t.name()).append(";\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return script.toString();
    }

    public static void executeVacuum(String pathSHVacuum) {
        StringBuilder err = new StringBuilder();
        System.out.println("Ejecutando Vacuum... " + new Date());

        Propiedad p = Propiedad.getCurrentInstance();
        File f = new File(p.getPathTmp() + "/vacuum-sobre5.sql");
        Archivo a = new Archivo(f);
        if (!f.exists()) {
            a.crearArchivo();
        }
        a.escribir(getVacummScript());

        String cmd = pathSHVacuum + " " + f.getPath();
        String out = SystemComand.executeComand(cmd, err);
        System.out.print(out);
        System.out.println("\n(" + f.getPath() + ")\n :) Vacuum Ejecutado!" + new Date());
    }

    public static void main(String[] args) {
        //System.out.println(getVacummScript());
        executeVacuum("/opt/sobre5/bin/vacuum-db.sh");
    }
}
