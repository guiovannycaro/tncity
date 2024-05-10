/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.control.entity.negocio;


import com.tncity.control.general.AbstractControl;
import com.tncity.facade.entity.PerfilFacade;
import com.tncity.facade.general.AbstractFacade;
import com.tncity.jpa.pojo.Perfil;
import com.tncity.security.FunctionalityXml;
import com.tncity.security.TreeUtil;
import com.tncity.util.Archivo;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "perfilControlNegocio")
@ViewScoped
public class PerfilControlNegocio extends AbstractControl<Perfil> implements Serializable {

    @EJB
    PerfilFacade facade;
    private TreeNode root;
    FunctionalityXml functionalityTree = new FunctionalityXml();
    //
    private TreeNode[] selectedNodes1;
    HashMap<Long, Long> mapFunc = new HashMap<>();

    public PerfilControlNegocio() {
        super(Perfil.class);
        attrOrd = "nombre";
        ascDesc = "ASC";
    }

    @Override
    protected AbstractFacade getFacade() {
        return facade;
    }

    @Override
    protected String displayObj(Perfil obj) {
        return obj.getNombre();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public FunctionalityXml getFunctionalityTree() {
        return functionalityTree;
    }

    public void setFunctionalityTree(FunctionalityXml functionalityTree) {
        this.functionalityTree = functionalityTree;
    }

    public TreeNode[] getSelectedNodes1() {
        return selectedNodes1;
    }

    public void setSelectedNodes1(TreeNode[] selectedNodes1) {
        this.selectedNodes1 = selectedNodes1;
    }

    public void initTree() {
        String xml = new Archivo(new File(this.getClass().getResource("/com/jclub/security/map-app.xml").getFile())).getContent();
        TreeUtil treeUtil = new TreeUtil(xml, FunctionalityXml.class);
        root = new DefaultTreeNode("Root", null);
        buildTree((FunctionalityXml) treeUtil.getRoot(), root);
    }

    public String getRecuperaFuncionalidadesByPerfil() {
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");
        mapFunc = facade.mapFuncionalidadesByPerfil(idperfil);
        return "";
    }

    private void buildTree(FunctionalityXml rootElement, TreeNode node) {

        for (FunctionalityXml func : rootElement.getLstFunctionality()) {
            TreeNode node1 = new DefaultTreeNode(func);
            calculaType(node1, func);
            calculaSelected(node1, func);
            //node1.setType(func.getType());
            node.getChildren().add(node1);

            buildTree(func, node1);
        }

        if (rootElement.getId() == -1L) {
            node.setExpanded(true);
        }

    }

    private void calculaType(TreeNode node, FunctionalityXml func) {

        if (func.getLstFunctionality().size() > 0) {
            node.setType("FOLDER");
        } else {
            node.setType("FUNC");
        }
    }
    
     private void calculaSelected(TreeNode node, FunctionalityXml func) {
        if (mapFunc.get(func.getId()) != null) {
            node.setSelected(true);
        } 
    }

    public void guardarFuncionalidades(TreeNode[] nodes) {

        setSuccessful(false);
        StringBuilder err = new StringBuilder();
        List<Long> lstId = new ArrayList<>();
        Integer idperfil = facesUtil.getFacesParamInteger("idperfil_");

        //Calculando lista de id funcionalidad a guardar
        if (nodes != null && nodes.length > 0) {
            for (TreeNode node : nodes) {
                FunctionalityXml func = (FunctionalityXml) node.getData();
                //System.out.println("id: " + func.getId() + " / name: " + func.getText());
                lstId.add(func.getId());
            }
        }

        facade.guardarPermisos(lstId, idperfil, err);
        if (err.toString().isEmpty()) {
            setSuccessful(true);
            facesUtil.msgOk("", "Permisos guardados !");
        } else {
            facesUtil.msgError("ALERTA: ", err.toString());
        }

    }

}
