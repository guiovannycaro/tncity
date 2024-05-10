/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.util;

import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

/**
 *
 * @author root
 */
public class Tiempo {

    public Tiempo() {
    }

    public String getFecha() {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        fech = sdf.format(new Date());
        return fech;
    }

    public String getFechaHora(Date fecha) {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fech = sdf.format(fecha);
        return fech;
    }

    public String getFecha(String formato, Date fecha) {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        fech = sdf.format(fecha);
        return fech;
    }

    public Date getFecha(String formato, String fecha) {
        Date fech = null;
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        try {
            fech = sdf.parse(fecha);
        } catch (Exception e) {
        }
        return fech;
    }

    public String getFechaHora() {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fech = sdf.format(new Date());
        return fech;
    }

    public String getHora() {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        fech = sdf.format(new Date());
        return fech;
    }

    public String getTiempo(String formato) {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        fech = sdf.format(new Date());
        return fech;
    }

    public String getTiempo(Date d, String formato) {
        if (d == null) {
            return "";
        }
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        fech = sdf.format(d);
        return fech;
    }

    public String[] getNomMeses(String fechIniS, String fechFinS) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechIni = null;
        Date fechFin = null;
        try {
            fechIni = sdf.parse(fechIniS);
            fechFin = sdf.parse(fechFinS);
        } catch (Exception e) {
        }

        String[] mes;
        Vector vec = new Vector();
        vec.add(new Date(fechIni.getTime()));
        while (fechFin.getTime() >= fechIni.getTime()) {
            int mAnt = fechIni.getMonth();
            fechIni.setTime(fechIni.getTime() + 24 * 60 * 60 * 1000);
            if (fechIni.getMonth() != mAnt) {
                Date dd = new Date();
                dd.setTime(fechIni.getTime());
                vec.add(dd);
            }
        }
        mes = new String[vec.size()];
        for (int i = 0; i < vec.size(); i++) {
            Date d = (Date) vec.get(i);
            String m = "";
            switch (d.getMonth()) {
                case Calendar.JANUARY:
                    m = "Enero";
                    break;
                case Calendar.FEBRUARY:
                    m = "Febrero";
                    break;
                case Calendar.MARCH:
                    m = "Marzo";
                    break;
                case Calendar.APRIL:
                    m = "Abril";
                    break;
                case Calendar.MAY:
                    m = "Mayo";
                    break;
                case Calendar.JUNE:
                    m = "Junio";
                    break;
                case Calendar.JULY:
                    m = "Julio";
                    break;
                case Calendar.AUGUST:
                    m = "Agosto";
                    break;
                case Calendar.SEPTEMBER:
                    m = "Septiembre";
                    break;
                case Calendar.OCTOBER:
                    m = "Octubre";
                    break;
                case Calendar.NOVEMBER:
                    m = "Noviembre";
                    break;
                case Calendar.DECEMBER:
                    m = "Diciembre";
                    break;
            }
            mes[i] = m + "-" + (d.getYear() + 1900) + "-" + (d.getMonth() + 1);
        }
        return mes;
    }

    public String getNomMes(Integer numMes) {
        String m = "";

        switch (numMes) {
            case Calendar.JANUARY:
                m = "Enero";
                break;
            case Calendar.FEBRUARY:
                m = "Febrero";
                break;
            case Calendar.MARCH:
                m = "Marzo";
                break;
            case Calendar.APRIL:
                m = "Abril";
                break;
            case Calendar.MAY:
                m = "Mayo";
                break;
            case Calendar.JUNE:
                m = "Junio";
                break;
            case Calendar.JULY:
                m = "Julio";
                break;
            case Calendar.AUGUST:
                m = "Agosto";
                break;
            case Calendar.SEPTEMBER:
                m = "Septiembre";
                break;
            case Calendar.OCTOBER:
                m = "Octubre";
                break;
            case Calendar.NOVEMBER:
                m = "Noviembre";
                break;
            case Calendar.DECEMBER:
                m = "Diciembre";
                break;
        }

        return m;
    }

    public String sumarMinutos(String hora, int numMinutos) {
        /**
         * @PARAMETERS: String hora: Hora inicial en formato "HH:mm:ss" String
         * numMinutos: Numero de minutos que se sumara
         *
         * @RETORNA String en formato "HH:mm:ss" con la nueva hora sumando los
         * minutos
         */
        String horFin = hora;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date dtInicial = sdf.parse(hora);
            long f = dtInicial.getTime() + 1000 * numMinutos * 60;
            dtInicial.setTime(f);
            return sdf.format(dtInicial);
        } catch (Exception e) {
            System.out.println("ERROR:sumando minutos a HORA->" + e);
            return horFin;
        }
    }

    public String[] descomponeFecha(Date d) {
        /**
         * @PARAMETERS String fechInicial:En formato "yyyy-MM-dd HH:mm:ss"
         * String fechFinal: En formato "yyyy-MM-dd HH:mm:ss"
         *
         * si fechInicial>fechFinal -> retorna segundos negativos;
         *
         * @RETORNA ARRAY: [0]=Segundos de diferencia [1]=Minutos de Diferendia
         * [2]=Horas de Diferencia [3]=Dias de Diferencia [4]=Meses de
         * Diferencia [5]=Annos de Diferencia
         */
        try {
            String[] result = new String[6];
            long x = (d.getTime()) / 1000;

            if ((int) (x / (60 * 60 * 24 * 30 * 12)) > 0) {
                result[5] = (int) (x / (60 * 60 * 24 * 30 * 12)) + "";
                x -= (int) (x / (60 * 60 * 24 * 30 * 12)) * (60 * 60 * 24 * 30 * 12);
            } else {
                result[5] = "0";
            }

            if ((int) (x / (60 * 60 * 24 * 30)) > 0) {
                result[4] = (int) (x / (60 * 60 * 24 * 30)) + "";
                x -= (int) (x / (60 * 60 * 24 * 30)) * (60 * 60 * 24 * 30);
            } else {
                result[4] = "0";
            }

            if ((int) (x / (60 * 60 * 24)) > 0) {
                result[3] = (int) (x / (60 * 60 * 24)) + "";
                x -= (int) (x / (60 * 60 * 24)) * (60 * 60 * 24);
            } else {
                result[3] = "0";
            }

            if ((int) (x / (60 * 60)) > 0) {
                result[2] = (int) (x / (60 * 60)) + "";
                x -= (int) (x / (60 * 60)) * (60 * 60);
            } else {
                result[2] = "0";
            }

            if ((int) (x / 60) > 0) {
                result[1] = (int) (x / 60) + "";
                x -= (int) (x / 60) * 60;
            } else {
                result[1] = "0";
            }

            result[0] = x + "";

            return result;
        } catch (Exception e) {
            System.out.println("ERROR:restando fechas->" + e);
            return null;
        }
    }

    public String descomponeFechaString(Date d) {
        String[] arr = descomponeFecha(d);
        String res = "";
        if (arr[5] != null && !arr[5].trim().equals("") && !arr[5].trim().equals("0")) {
            res += arr[5] + " año(s),";
        }
        if (arr[4] != null && !arr[4].trim().equals("") && !arr[4].trim().equals("0")) {
            res += arr[4] + " mes(es),";
        }
        if (arr[3] != null && !arr[3].trim().equals("") && !arr[3].trim().equals("0")) {
            res += arr[3] + " dia(s),";
        }
        if (arr[2] != null && !arr[2].trim().equals("") && !arr[2].trim().equals("0")) {
            res += arr[2] + " h,";
        }
        if (arr[1] != null && !arr[1].trim().equals("") && !arr[1].trim().equals("0")) {
            res += arr[1] + " m,";
        }
        if (arr[0] != null && !arr[0].trim().equals("") && !arr[0].trim().equals("0")) {
            res += arr[0] + " s,";
        }
        if (!res.trim().equals("")) {
            res = res.substring(0, res.length() - 1);
        }

        return res;
    }

    public String obtenerRestaTiempoConFormato(String fechaIni, String fechaFin) {
        String time = "";
        String seg = "";
        String min = "";
        String hor = "";
        String dia = "";
        String[] tSol = this.restaFecha(fechaIni, fechaFin);
        if (Integer.parseInt(tSol[0]) >= 10) {
            seg = tSol[0];
        } else {
            seg = "0" + tSol[0];
        }
        if (Integer.parseInt(tSol[1]) >= 10) {
            min = tSol[1];
        } else {
            min = "0" + tSol[1];
        }
        if (Integer.parseInt(tSol[2]) >= 10) {
            hor = tSol[2];
        } else {
            hor = "0" + tSol[2];
        }
        if (Integer.parseInt(tSol[3]) > 0) {
            dia = tSol[3] + " dia(s) - ";
        } else {
            dia = "";
        }
        time = dia + hor + ":" + min + ":" + seg;
        return time;
    }

    public boolean validaFecha(String fech) {
        //Esta funcion valida una fecha en el siguiente formato: AAAA-MM-DD
        //EJ: 2006-05-01
        //PARAMETROS:Recibe un objeto un STRING con la fecha
        //RETORNO: False=si, La fecha es incorrecta;  TRUE=si, la fecha es correcta
        fech = fech.trim();
        if (fech.length() != 10 || fech.substring(4, 5).compareTo("-") != 0 || fech.substring(7, 8).compareTo("-") != 0) {
            //JOptionPane.showMessageDialog(null,"Formato de fecha no válido","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int dia = Integer.parseInt(fech.substring(8, 10));
        int mes = Integer.parseInt(fech.substring(5, 7));
        int yea = Integer.parseInt(fech.substring(0, 4));

        if (yea < 1900 || yea > 2100) {
            //JOptionPane.showMessageDialog(null,"Año no válido!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (mes < 1 || mes > 12) {
            //JOptionPane.showMessageDialog(null,"Mes no válido!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (dia < 1 || dia > 31) {//Meses con 31 dias
            //JOptionPane.showMessageDialog(null,"Día no válido!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {//Meses con 30 dias
            //JOptionPane.showMessageDialog(null,"Fecha no válida!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (mes == 2 && dia > 28) {//Meses con 28 dias
            //JOptionPane.showMessageDialog(null,"Fecha no válida!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }//end method

    public boolean validaHora(String hora) {
        //Método para hora de un objeto text en fomrato HH:MM:SS 
        hora = hora.trim();
        if (hora.compareTo("") == 0) {
            return false;
        }
        if (hora.length() > 8) {
            //JOptionPane.showMessageDialog(null,"Introdujo una cadena mayor a 8 caracteres!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (hora.length() != 8) {
            //JOptionPane.showMessageDialog(null,"Introducir HH:MM:SS!","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int a = Integer.parseInt(hora.charAt(0) + ""); //<=2
        int b = Integer.parseInt(hora.charAt(1) + ""); //<4
        char c = hora.charAt(2); //:
        int d = Integer.parseInt(hora.charAt(3) + ""); //<=5
        char e = hora.charAt(5); //:
        int f = Integer.parseInt(hora.charAt(6) + ""); //<=5
        if ((a == 2 && b > 3) || (a > 2)) {
            //JOptionPane.showMessageDialog(null,"El valor que introdujo en la Hora no corresponde, introduzca un digito entre 00 y 23","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (d > 5) {
            //JOptionPane.showMessageDialog(null,"El valor que introdujo en los minutos no corresponde, introduzca un digito entre 00 y 59","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (f > 5) {
            //JOptionPane.showMessageDialog(null,"El valor que introdujo en los segundos no corresponde","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (c != ':' || e != ':') {
            //JOptionPane.showMessageDialog(null,"Introduzca el caracter ':' para separar la hora, los minutos y los segundos","Enterprise",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void setHora(javax.swing.JTextField objText) {
        /**
         * FUNCION QUE ESTABLECE LA HORA(militar) EN UN <input type='text'> si:
         * objText.value='nn.mn' o 'n.m' -> objText.value='nn:mm:00'
         *
         * Ej1: si: objText.value='5' -> objText.value='05:00:00' si:
         * objText.value='5.15' -> objText.value='05:15:00' si:
         * objText.value='5.3' -> objText.value='05:30:00' si:
         * objText.value='5.0' -> objText.value='05:00:00' si:
         * objText.value='5.45' -> objText.value='05:45:00'
         *
         */
        String cad = objText.getText().trim();
        int punto = 0;
        String hor = "";
        String min = "";
        for (int i = 0; i < cad.length(); i++) {
            if (cad.charAt(i) == '.') {
                punto = i;
                break;
            }
        }//for
        if (punto == 0) {
            int aux = 0;
            try {
                aux = Integer.parseInt(cad);
            } catch (Exception e) {
                return;
            }
            if (Integer.parseInt(cad) > 23 || Integer.parseInt(cad) < 0) {
                return;
            } else {
                if (Integer.parseInt(cad) < 10) {
                    hor = "0" + Integer.parseInt(cad);
                } else {
                    hor = Integer.parseInt(cad) + "";
                }
                min = "00";
            }
        }//if
        else {
            String horC = cad.substring(0, punto);
            String minC = cad.substring(punto + 1, cad.length());
            int aux = 0;
            try {
                aux = Integer.parseInt(horC);
            } catch (Exception e) {
                return;
            }
            aux = 0;
            try {
                aux = Integer.parseInt(minC);
            } catch (Exception e) {
                return;
            }
            if (Integer.parseInt(horC) < 0 || Integer.parseInt(horC) > 23 || Integer.parseInt(minC) < 0 || Integer.parseInt(minC) > 59) {
                return;
            }
            if (Integer.parseInt(horC) < 10) {
                hor = "0" + horC;
            } else {
                hor = horC;
            }
            if (Integer.parseInt(minC) < 10) {
                min = "0" + minC;
            } else {
                min = minC;
            }
        }//else
        objText.setText(hor + ":" + min + ":" + "00");
    }//end method

    public String getFechaFormatoEspanol_conHora(Date d) {
        SimpleDateFormat formatoEspanol = new SimpleDateFormat("dd'-'MMM'-'yyyy HH:mm:ss", new Locale("ES"));
        return formatoEspanol.format(d);
    }

    public String getFechaFormatoEspanol_sinHora(Date d) {
        SimpleDateFormat formatoEspanol = new SimpleDateFormat("dd'-'MMM'-'yyyy", new Locale("ES"));
        return formatoEspanol.format(d);
    }

    public String getDiaEspanol(Date d) {
        SimpleDateFormat formatoEspanol = new SimpleDateFormat("E", new Locale("EN"));
        String di = formatoEspanol.format(d);
        if (di.trim().toUpperCase().compareTo("MON") == 0) {
            return "LU";
        }
        if (di.trim().toUpperCase().compareTo("TUE") == 0) {
            return "MA";
        }
        if (di.trim().toUpperCase().compareTo("WED") == 0) {
            return "MI";
        }
        if (di.trim().toUpperCase().compareTo("THU") == 0) {
            return "JU";
        }
        if (di.trim().toUpperCase().compareTo("FRI") == 0) {
            return "VI";
        }
        if (di.trim().toUpperCase().compareTo("SAT") == 0) {
            return "SA";
        }
        if (di.trim().toUpperCase().compareTo("SUN") == 0) {
            return "DO";
        }
        return di;
    }

    public int getNumDias(Date ini, Date fin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            ini = sdf.parse(ini.toString().substring(0, 10) + " 00:00:00");
            fin = sdf.parse(fin.toString().substring(0, 10) + " 00:00:00");
        } catch (Exception e) {
        }

        long tiempoMora = fin.getTime() - ini.getTime();
        int t = (int) Math.ceil(tiempoMora * 1.0 / 1000 / 60 / 60 / 24);

        return t;
    }

    public Date parseDate(String fech) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(fech);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Date parseDate2(String fech) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(fech);
        } catch (Exception e) {
        }

        return null;
    }

    public List<Date> separarFechas(Date fIni, Date fFin, long tiempoSeparacion) {
        long dif = fFin.getTime() - fIni.getTime();
        if (dif < 0) {
            System.out.println("CIUDADO: separando fechas, F.Inicial(" + fIni + ") > F.Final(" + fFin + ")");
            return new ArrayList();
        }

        List lst = new ArrayList();
        int n = (int) (dif / tiempoSeparacion);
        long acum = fIni.getTime();
        for (int i = 0; i < n; i++) {
            Date d = new Date();
            d.setTime(acum);
            lst.add(d);
            acum += tiempoSeparacion;
        }

        return lst;
    }

    public static String getTiempoDuracion(Date fechaIni, Date fechaFin) {
        String formatFecha = "yyyy-MM-dd HH:mm:ss";
        if (fechaIni != null && fechaFin != null) {
            return new Tiempo().restaFechaString(new Tiempo().getFecha(formatFecha, fechaIni), new Tiempo().getFecha(formatFecha, fechaFin));
        }
        return null;
    }

    public static String getTiempoDuracion(String fechaIni, String fechaFin) {
        if (fechaIni != null && fechaFin != null) {
            return new Tiempo().restaFechaString(fechaIni, fechaFin);
        }
        return null;
    }

    public static String restaFechaString(Date fechInicial, Date fechFinal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return restaFechaString(sdf.format(fechInicial), sdf.format(fechFinal));
    }

    public static String restaFechaString(String fechInicial, String fechFinal) {
        String[] arr = restaFecha(fechInicial, fechFinal);
        String res = "";
        if (arr[5] != null && !arr[5].trim().equals("") && !arr[5].trim().equals("0")) {
            res += arr[5] + " año(s),";
        }
        if (arr[4] != null && !arr[4].trim().equals("") && !arr[4].trim().equals("0")) {
            res += arr[4] + " mes(es),";
        }
        if (arr[3] != null && !arr[3].trim().equals("") && !arr[3].trim().equals("0")) {
            res += arr[3] + " dia(s),";
        }
        if (arr[2] != null && !arr[2].trim().equals("") && !arr[2].trim().equals("0")) {
            res += arr[2] + " h,";
        }
        if (arr[1] != null && !arr[1].trim().equals("") && !arr[1].trim().equals("0")) {
            res += arr[1] + " m,";
        }
        if (arr[0] != null && !arr[0].trim().equals("") && !arr[0].trim().equals("0")) {
            res += arr[0] + " s,";
        }
        if (!res.trim().equals("")) {
            res = res.substring(0, res.length() - 1);
        }

        return res;
    }

    public static String[] restaFecha(String fechInicial, String fechFinal) {
        /**
         * @PARAMETERS String fechInicial:En formato "yyyy-MM-dd HH:mm:ss"
         * String fechFinal: En formato "yyyy-MM-dd HH:mm:ss"
         *
         * si fechInicial>fechFinal -> retorna segundos negativos;
         *
         * @RETORNA ARRAY: [0]=Segundos de diferencia [1]=Minutos de Diferendia
         * [2]=Horas de Diferencia [3]=Dias de Diferencia [4]=Meses de
         * Diferencia [5]=Annos de Diferencia
         */
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dtFinal = sdf.parse(fechFinal);
            Date dtInicial = sdf.parse(fechInicial);

            String[] result = new String[6];
            long x = (dtFinal.getTime() - dtInicial.getTime()) / 1000;

            if ((int) (x / (60 * 60 * 24 * 30 * 12)) > 0) {
                result[5] = (int) (x / (60 * 60 * 24 * 30 * 12)) + "";
                x -= (int) (x / (60 * 60 * 24 * 30 * 12)) * (60 * 60 * 24 * 30 * 12);
            } else {
                result[5] = "0";
            }

            if ((int) (x / (60 * 60 * 24 * 30)) > 0) {
                result[4] = (int) (x / (60 * 60 * 24 * 30)) + "";
                x -= (int) (x / (60 * 60 * 24 * 30)) * (60 * 60 * 24 * 30);
            } else {
                result[4] = "0";
            }

            if ((int) (x / (60 * 60 * 24)) > 0) {
                result[3] = (int) (x / (60 * 60 * 24)) + "";
                x -= (int) (x / (60 * 60 * 24)) * (60 * 60 * 24);
            } else {
                result[3] = "0";
            }

            if ((int) (x / (60 * 60)) > 0) {
                result[2] = (int) (x / (60 * 60)) + "";
                x -= (int) (x / (60 * 60)) * (60 * 60);
            } else {
                result[2] = "0";
            }

            if ((int) (x / 60) > 0) {
                result[1] = (int) (x / 60) + "";
                x -= (int) (x / 60) * 60;
            } else {
                result[1] = "0";
            }

            result[0] = x + "";

            return result;
        } catch (Exception e) {
            System.out.println("ERROR:restando fechas->" + e);
            return null;
        }
    }

    public static String getFecha2(String formato, Date fecha) {
        String fech = "";
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        fech = sdf.format(fecha);
        return fech;
    }

    public static Date getAniosAtras(long numAnios, Date hoy) {
        long tHoy = hoy.getTime();
        long tFinal = tHoy - (1000l * 60l * 60l * 24l * 365l * numAnios);
        Date dateFinal = new Date();
        dateFinal.setTime(tFinal);

        return dateFinal;
    }

    public static int calculaEdad(Date fechaNacim) {
        if (fechaNacim == null) {
            return 0;
        }

        Calendar cal = Calendar.getInstance(); // Fecha Actual
        int hoyAno = cal.get(Calendar.YEAR);
        int hoyMes = cal.get(Calendar.MONTH);
        int hoyDia = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(fechaNacim); // Fecha Nacimiento Persona
        int anos = hoyAno - cal.get(Calendar.YEAR);
        int nacimMes = cal.get(Calendar.MONTH);
        int nacimDia = cal.get(Calendar.DAY_OF_MONTH);
        if (nacimMes == hoyMes) { // Mimsmo Mes
            int tmp = hoyDia - nacimDia;
            if (tmp < 0) {
                return anos = anos - 1;
            }
            if (tmp >= 0) {
                return anos = anos;
            }
        }
        if (nacimMes < hoyMes) {
            return anos = anos;
        } else {
            return anos = anos - 1;
        }
    }

    public static Date finalDay(Date d) {
        /*long tim = d.getTime();
         tim = tim + 24 * 60 * 60 * 1000 - 1000;
         d.setTime(tim);
         return d;*/

        Calendar c = Calendar.getInstance();
        c.setTime(d);

        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return c.getTime();
    }

    public static int cantidadDiaEntreFechas(String dia, Date fechaIni, Date fechaFin) {

        if (fechaIni == null || fechaFin == null || dia == null) {
            return 0;
        }

        if (fechaIni.getTime() > fechaFin.getTime()) {
            return 0;
        }

        //Calculando cantidad del dia (Para el Dia seleccionado)
        boolean finCorte = false;
        //Date fechaAux = new Date(fechaIni.getTime());
        String diaCorte = dia;
        int cont = 0;
        int dias = 0;

        Calendar cIni = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        cIni.setTime(fechaIni);
        cFin.setTime(fechaFin);
        cIni.set(Calendar.HOUR_OF_DAY, 0);
        cIni.set(Calendar.MINUTE, 0);
        cIni.set(Calendar.SECOND, 0);
        cIni.set(Calendar.MILLISECOND, 0);
        cFin.set(Calendar.HOUR_OF_DAY, 0);
        cFin.set(Calendar.MINUTE, 0);
        cFin.set(Calendar.SECOND, 0);
        cFin.set(Calendar.MILLISECOND, 0);

        Date fechaAux = cIni.getTime();

        while (!finCorte) {
            String diaEntra = new Tiempo().getDiaEspanol(fechaAux);

            if (diaEntra.equals(diaCorte)) {
                dias++;
            }

            if (fechaAux.equals(cFin.getTime())) {
                finCorte = true;
            }

            cont++;

            fechaAux.setTime(new Tiempo().sumarDias(cIni.getTime(), cont).getTime());

        }

        return dias;
    }

    public static List<Date> listFechasEntreCortes(Date fechaIni, Date fechaFin) {

        if (fechaIni == null || fechaFin == null) {
            return null;
        }

        if (fechaIni.getTime() > fechaFin.getTime()) {
            return null;
        }

        List<Date> lstF = new ArrayList<>();
        Calendar cIni = Calendar.getInstance();
        Calendar cFin = Calendar.getInstance();
        boolean finCorte = false;
        int cont = 0;

        cIni.setTime(fechaIni);
        cFin.setTime(fechaFin);

        cIni.set(Calendar.HOUR_OF_DAY, 0);
        cIni.set(Calendar.MINUTE, 0);
        cIni.set(Calendar.SECOND, 0);
        cIni.set(Calendar.MILLISECOND, 0);

        cFin.set(Calendar.HOUR_OF_DAY, 0);
        cFin.set(Calendar.MINUTE, 0);
        cFin.set(Calendar.SECOND, 0);
        cFin.set(Calendar.MILLISECOND, 0);

        try {
            while (!finCorte) {

                Date fechaIncrementa = new Date(new Tiempo().sumarDias(cIni.getTime(), cont).getTime());

                if (fechaIncrementa.equals(cFin.getTime())) {
                    finCorte = true;
                }

                Date fechaEntra = new Date(fechaIncrementa.getTime());
                lstF.add(fechaEntra);

                cont++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lstF;
    }

    public static List<Date> corteSemanal(String dia, Date fechaIni, Date fechaFin) {

        List<Date> lstF = new ArrayList();
        Date fechaFinAux = new Date(fechaFin.getTime());

        //Calculando cortes Semanales (Para el Dia seleccionado)
        boolean finCorte = false;
        Date fechaAux = new Date(fechaIni.getTime());
        String diaCorte = dia;
        int cont = 1;

        while (!finCorte) {
            String diaEntra = new Tiempo().getDiaEspanol(fechaAux);
            if (diaEntra.equals(diaCorte)) {
                lstF.add(new Date(fechaAux.getTime()));
            }
            if (fechaAux.equals(fechaFin)) {
                finCorte = true;
            }
            fechaAux.setTime(new Tiempo().sumarDias(fechaIni, cont).getTime());
            cont++;
        }

        for (int i = 0; i < lstF.size(); i++) {
            Date dFinal = new Date(new Tiempo().finalDay(lstF.get(i)).getTime());
            Date nFecha = new Date(dFinal.getTime());
            lstF.set(i, nFecha);
        }

        fechaFinAux.setTime(new Tiempo().finalDay(fechaFinAux).getTime());

        Date ultimo = new Date(lstF.get(lstF.size() - 1).getTime());

        if (ultimo.compareTo(fechaFinAux) != 0) {
            lstF.add(new Date(fechaAux.getTime() - 1000));
        }

        return lstF;
    }

    public static List<Date> corteQuincenal(Date fechaIni, Date fechaFin) {

        List<Date> lstF = new ArrayList();
        Date fechaFinAux = new Date(fechaFin.getTime());

        //Calculando cortes Quincelanles (Para el Dia 15 y 30)
        boolean finCorte = false;
        Date fechaAux = new Date(fechaIni.getTime());
        int cont = 1;

        while (!finCorte) {

            int diaEntra = new Tiempo().getNumDia(fechaAux);
            int numMes = new Tiempo().getNumMes(fechaAux);

            if (diaEntra == 15) {
                lstF.add(new Date(fechaAux.getTime()));
            }

            if ((diaEntra == 28 || diaEntra == 29) && numMes == 2) {
                lstF.add(new Date(fechaAux.getTime()));
            }

            if (diaEntra == 30) {
                lstF.add(new Date(fechaAux.getTime()));
            }

            if (fechaAux.equals(fechaFin)) {
                finCorte = true;
            }
            fechaAux.setTime(new Tiempo().sumarDias(fechaIni, cont).getTime());
            cont++;
        }

        for (int i = 0; i < lstF.size(); i++) {
            Date dFinal = new Date(new Tiempo().finalDay(lstF.get(i)).getTime());
            Date nFecha = new Date(dFinal.getTime());
            lstF.set(i, nFecha);
        }

        fechaFinAux.setTime(new Tiempo().finalDay(fechaFinAux).getTime());

        Date ultimo = new Date(lstF.get(lstF.size() - 1).getTime());

        if (ultimo.compareTo(fechaFinAux) != 0) {
            lstF.add(new Date(fechaAux.getTime() - 1000));
        }

        return lstF;
    }

    public static List<Date> corteMensual(Date fechaIni, Date fechaFin) {

        List<Date> lstF = new ArrayList();
        Date fechaFinAux = new Date(fechaFin.getTime());

        //Calculando dias para el final del Mes
        int diaDesde = new Tiempo().getNumDia(fechaIni);
        int diaUltimo = getUltimoDiaDelMes(fechaIni);

        int diasDif = diaUltimo - diaDesde;

        Date fechaCorte = new Date(new Tiempo().sumarDias(fechaIni, diasDif).getTime());
        fechaCorte.setTime(new Tiempo().finalDay(fechaCorte).getTime());

        lstF.add(new Date(fechaCorte.getTime()));

        fechaCorte.setTime(new Date(fechaCorte.getTime() + 1000).getTime());

        //Calculando cortes Mensuales
        while (new Tiempo().getNumMes(fechaCorte) != new Tiempo().getNumMes(fechaFin)) {

            diaDesde = new Tiempo().getNumDia(fechaCorte);
            diaUltimo = getUltimoDiaDelMes(fechaCorte);

            diasDif = diaUltimo - diaDesde;

            Date fechaFinMes = new Date(new Tiempo().sumarDias(fechaCorte, diasDif).getTime());
            fechaCorte.setTime(new Date(new Tiempo().finalDay(fechaFinMes).getTime()).getTime());

            lstF.add(new Date(fechaCorte.getTime()));

            Date nFecha = new Date(fechaCorte.getTime() + 1000);

            fechaCorte.setTime(nFecha.getTime());

        }

        fechaFinAux.setTime(new Tiempo().finalDay(fechaFinAux).getTime());

        Date ultimo = new Date(lstF.get(lstF.size() - 1).getTime());

        if (ultimo.compareTo(fechaFinAux) != 0) {
            lstF.add(new Date(fechaFinAux.getTime() - 1000));

        }

        return lstF;

    }

    public static List<Date> corteAnual(Date fechaIni, Date fechaFin) {
        List<Date> lstF = new ArrayList();

        int anoIni = Integer.parseInt(getFecha2("yyyy", fechaIni));
        int anoFin = Integer.parseInt(getFecha2("yyyy", fechaFin));

        int anoDiff = anoFin - anoIni;

        while (anoDiff > 0) {
            Calendar calendar = new GregorianCalendar(anoIni, 11, 31, 23, 59, 59);//Calculando fin del Año
            System.out.println("calendar vale " + calendar.getTime());
            Date n = new Date(calendar.getTime().getTime());
            lstF.add(new Date(n.getTime()));
            Date nAux = new Date(n.getTime() + 1000);
            anoIni = Integer.parseInt(getFecha2("yyyy", nAux));
            anoDiff = anoFin - anoIni;
        }

        Date u = new Date(finalDay(fechaFin).getTime());
        lstF.add(new Date(u.getTime()));

        return lstF;

    }

    public static long getMiliSegFromHora(String fecha) {//FECHA FORMATO HH:mm:ss 00:00:00.0
        double result = 0D;

        String[] arr = fecha.split(":");

        try {
            result += Double.parseDouble(arr[0]) * (double) (60 * 60 * 1000);
            result += Double.parseDouble(arr[1]) * (double) (60 * 1000);
            result += Double.parseDouble(arr[2]) * (double) 1000;

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return (long) result;
    }

    public static String getHoraFromMiliSeg(long hora) {
        String result = "";

        try {
            int h = 60 * 60 * 1000;
            int m = 60 * 1000;

            int dh = ((int) hora / h);
            result += ((dh < 10) ? "0" + dh : dh) + ":";
            int rh = (int) hora % h;
            int dm = ((int) rh / m);
            result += ((dm < 10) ? "0" + dm : dm) + ":";
            int rm = rh % m;
            double ds = (double) rm / 1000;
            result += (ds < 10) ? "0" + ds : ds;

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return result;
    }

    public int getNumMes(Date d) {
        SimpleDateFormat formatoEspanol = new SimpleDateFormat("MM", new Locale("EN"));
        String di = formatoEspanol.format(d);
        return Integer.parseInt(di);
    }

    public int getNumDia(Date d) {
        SimpleDateFormat formatoEspanol = new SimpleDateFormat("dd", new Locale("EN"));
        String di = formatoEspanol.format(d);
        return Integer.parseInt(di);
    }

    public static int getPrimerDiaDelMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    public static int getUltimoDiaDelMes(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date sumarMinutos(Date date, int minutes) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.add(Calendar.MINUTE, minutes);
        return calendarDate.getTime();
    }

    public static Date sumarSegundos(Date date, int seconds) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.add(Calendar.SECOND, seconds);
        return calendarDate.getTime();
    }

    public static Date restarMinutos(Date date, int minutes) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.set(Calendar.MINUTE, calendarDate.get(Calendar.MINUTE) - minutes);
        return calendarDate.getTime();
    }

    public static Date restarSegundos(Date date, int seconds) {
        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);
        calendarDate.set(Calendar.SECOND, calendarDate.get(Calendar.SECOND) - seconds);
        return calendarDate.getTime();
    }

    public static Date sumarDias(Date date, int days) {
        return sumarMinutos(date, 60 * 24 * days);
    }

    public int getNumMinutos(Date ini, Date fin) {
        long t1 = ini.getTime();
        long t2 = fin.getTime();

        long diff = t2 - t1;

        int result = (int) diff / (1000 * 60);

        return result;
    }

    public int getNumSegundos(Date ini, Date fin) {
        long t1 = ini.getTime();
        long t2 = fin.getTime();

        long diff = t2 - t1;

        int result = (int) diff / (1000);

        return result;
    }

    /**
     * Límites en Fechas (Domingos límites) para analizar semanas del mes
     *
     * @param yyyy: Año
     * @param month: Mes de 0 a 11
     * @return
     */
    public List<Date> listDomingosLimitesMes(Integer yyyy, int month) {
        List<Date> days = new ArrayList<>();

        boolean isFinishedMonth = isMesTerminado(yyyy, month);

        //HOY
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        //Primer día del mes
        Calendar firstDayMonth = Calendar.getInstance();
        firstDayMonth.set(yyyy, month, 1);

        //Último día del mes
        int lastDayMonth = firstDayMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        //Todos Domingos del Mes
        List<Date> sundays = new ArrayList<>();
        for (int i = 1; i <= lastDayMonth; i++) {
            Calendar date = Calendar.getInstance();
            date.set(yyyy, month, i);

            if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                if (now.after(date) && !isFinishedMonth) {
                    sundays.add(date.getTime());
                }

                if (isFinishedMonth) {
                    sundays.add(date.getTime());
                }
            }
        }

        //Primer Domingo del Mes
        if (!sundays.isEmpty()) {
            Calendar firstSunday = Calendar.getInstance();
            firstSunday.setTime(sundays.get(0));

            //Último Domingo del Mes
            Calendar lastSunday = Calendar.getInstance();
            lastSunday.setTime(sundays.get(sundays.size() - 1));

            if (firstSunday.get(Calendar.DAY_OF_MONTH) != 1) {
                int mesAnt = (month == Calendar.JANUARY) ? Calendar.DECEMBER : month - 1;
                int year = (month == Calendar.JANUARY) ? yyyy - 1 : yyyy;
                days.add(lastSundayMonth(year, mesAnt));
            }

            days.addAll(sundays);

            if (isFinishedMonth) {
                if (lastSunday.get(Calendar.DAY_OF_MONTH) != lastDayMonth) {
                    int mesSig = (month == Calendar.DECEMBER) ? Calendar.JANUARY : month + 1;
                    int year = (month == Calendar.DECEMBER) ? yyyy + 1 : yyyy;
                    days.add(firstSundayMonth(year, mesSig));
                }
            }
        }

        return days;
    }

    public Date firstSundayMonth(Integer yyyy, int month) {
        Date firstSunday = null;

        //Primer día del mes
        Calendar firstDayMonth = Calendar.getInstance();
        firstDayMonth.set(yyyy, month, 1);

        //Último día del mes
        int lastDayMonth = firstDayMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        //Obtiene Primer Domingo del Mes
        for (int i = 1; i <= lastDayMonth; i++) {
            Calendar fecha = Calendar.getInstance();
            fecha.set(yyyy, month, i);

            if (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                Date d = fecha.getTime();
                firstSunday = d;
                break;
            }
        }

        return firstSunday;
    }

    public Date lastSundayMonth(Integer yyyy, int month) {
        Date lastSunday = null;

        //Primer día del mes
        Calendar firstDayMonth = Calendar.getInstance();
        firstDayMonth.set(yyyy, month, 1);

        //Último día del mes
        int lastDayMonth = firstDayMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        //Obtiene Último Domingo del Mes
        for (int i = 1; i <= lastDayMonth; i++) {
            Calendar fecha = Calendar.getInstance();
            fecha.set(yyyy, month, i);

            if (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                Date d = fecha.getTime();
                lastSunday = d;
            }
        }

        return lastSunday;
    }

    public boolean isMesTerminado(Integer yyyy, int month) {
        //Primer día del mes
        Calendar firstDayMonth = Calendar.getInstance();
        firstDayMonth.set(yyyy, month, 1);

        //Último día del mes
        int ldm = firstDayMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar lastDayMonth = Calendar.getInstance();
        lastDayMonth.set(yyyy, month, ldm);

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        if (lastDayMonth.after(now)) {
            return false;
        }

        return true;
    }

    public static Date setHour2359(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static Date setHour0000(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    public static void main(String args[]) {
        String tim = new Tiempo().getFechaHora();
        System.out.println(tim.replaceAll(" ", "_").replaceAll(":", "_").substring(0, tim.length() - 3));
        System.out.println(new Tiempo().getHora());
        System.out.println(new Tiempo().getTiempo("yyyy-MM-dd HH:mm:ss"));
        String[] res = new Tiempo().restaFecha("2007-12-31 23:59:59", "2008-01-01 00:01:45");
        System.out.println(res[3] + " dias," + res[2] + " horas," + res[1] + " minutos," + res[0] + " segundos");
        System.out.println("SUMANDO MINUTOS: " + new Tiempo().sumarMinutos("06:00:00", 62));
        //System.out.println("PERIODO ACTUAL: "+new Tiempo().getPrriodoActual());
        System.out.println("DIA DE LA SEMANA=" + new Tiempo().getDiaEspanol(new Date()));

        String[] m = new Tiempo().getNomMeses("2010-01-01", "2010-04-02");
        for (int i = 0; i < m.length; i++) {
            System.out.println(m[i]);
        }

        Tiempo t = new Tiempo();
        long mesMil = (long) (1000 * 60 * 60 * 24) * (long) (30);
        List l = t.separarFechas(t.parseDate("2012-01-01 00:00:00.0"), t.parseDate("2012-07-31 23:59:59.0"), mesMil);
        System.out.println("l=" + l.size());

        System.out.println(getHoraFromMiliSeg(3746011));

        for (int i = 0; i < 12; i++) {
            System.out.println("MM: " + i);
            for (Date f : t.listDomingosLimitesMes(2018, i)) {
                System.out.println("f: " + new SimpleDateFormat("yyyy-MM-dd").format(f));
            }
        }
    }

}
