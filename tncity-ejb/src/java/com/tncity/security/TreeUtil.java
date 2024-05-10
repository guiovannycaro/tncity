/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tncity.security;


import com.tncity.util.JaxbUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeUtil {

    private AbtractTreeXml root;
    private AbtractTreeXml elementFind;
    private Class treeClass;

    public TreeUtil(Class treeClass) {
        try {
            root = (AbtractTreeXml) treeClass.newInstance();
            root.setId(new Long(-1));
            this.treeClass = treeClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeUtil(long id, Class treeClass) {
        try {
            root = (AbtractTreeXml) treeClass.newInstance();
            root.setId(new Long(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TreeUtil(String xml, Class treeClass) {
        root = (AbtractTreeXml) JaxbUtil.xmlToObject(xml, treeClass);
    }

    public TreeUtil(AbtractTreeXml root) {
        this.root = root;
    }

    public String getXml() {
        return JaxbUtil.objectToXML(root);
    }

    public AbtractTreeXml getRoot() {
        return root;
    }

    public void addChildren(AbtractTreeXml element, long idPatern) {
        elementFind = null;
        findElement(idPatern, root);
        if (elementFind != null) {
            elementFind.getLstElements().add(element);
        }
    }

    public void dragAndDrop(AbtractTreeXml elDrag, AbtractTreeXml elDrop, int indexDrop) {
        removeElement(elDrag.getId());
        addChildrenDragAndDrop(elDrag, elDrop.getId(), indexDrop);
    }

    private void addChildrenDragAndDrop(AbtractTreeXml element, Long elDrop, int indexDrop) {
        findNode(elDrop);
        List<AbtractTreeXml> lstEl = elementFind.getLstElements();

        if (indexDrop >= lstEl.size()) {
            lstEl.add(element);
            return;
        }

        List<AbtractTreeXml> lstAux = new ArrayList<>();
        for (int i = indexDrop; i < lstEl.size(); i++) {
            lstAux.add(lstEl.get(i));
        }

        //Set
        lstEl.set(indexDrop, element);

        //Remove
        lstEl.removeAll(lstAux);

        //Add
        for (AbtractTreeXml el : lstAux) {
            lstEl.add(el);
        }
    }

    private void findElement(Long id, AbtractTreeXml rootFind) {
        if (id.equals(new Long(-1))) {
            elementFind = root;
            return;
        }
        for (AbtractTreeXml elem : rootFind.getLstElements()) {
            if (elem.getId().equals(id)) {
                elementFind = elem;
                return;
            } else {
                findElement(id, elem);
            }
        }
    }

    public void removeElement(long id) {
        elementFind = null;
        findPattern(id, root);
        if (elementFind != null) {
            int i = -1;
            for (AbtractTreeXml element : elementFind.getLstElements()) {
                i++;
                if (element.getId().equals(id)) {
                    break;
                }
            }
            if (i >= 0) {
                elementFind.getLstElements().remove(i);
            }
        }
    }

    public void changeElementId(long oldId, long newId) {
        elementFind = null;
        findPattern(oldId, root);
        if (elementFind != null) {
            int i = -1;
            for (AbtractTreeXml element : elementFind.getLstElements()) {
                i++;
                if (element.getId().equals(oldId)) {
                    element.setId(newId);
                }
            }
        }
    }

    private void findPattern(Long id, AbtractTreeXml rootFind) {
        if (id.equals(new Long(-1))) {
            elementFind = root;
            return;
        }
        for (AbtractTreeXml elem : rootFind.getLstElements()) {
            if (elem.getId().equals(id)) {
                elementFind = rootFind;
                return;
            } else {
                findPattern(id, elem);
            }
        }
    }

    public AbtractTreeXml getParent(Long id) {
        elementFind = null;
        findPattern(id, root);
        return elementFind;
    }

    public AbtractTreeXml findNode(Long id) {
        elementFind = null;
        findElement(id, root);
        return elementFind;
    }

    private List<AbtractTreeXml> lstElements = new ArrayList<>();

    private void listElements(AbtractTreeXml parent) {
        lstElements.add(parent);
        for (AbtractTreeXml e : parent.getLstElements()) {
            listElements(e);
        }
    }

    public List<AbtractTreeXml> listElements() {
        lstElements = new ArrayList<>();
        listElements(root);
        return lstElements;
    }

    public TreeUtil copyTree(TreeUtil oriTree, HashMap<Long, Long> mapaIds) {
        TreeUtil newTree = new TreeUtil(oriTree.getXml(), treeClass);

        for (Long oldId : mapaIds.keySet()) {
            if (mapaIds.get(oldId) != null) {
                newTree.changeElementId(oldId, mapaIds.get(oldId));
            }
        }

        return newTree;
    }

    public static void main(String[] args) {
        TreeUtil util = new TreeUtil(FunctionalityXml.class);

        FunctionalityXml h1 = new FunctionalityXml(1,"ROOT");
        FunctionalityXml h2 = new FunctionalityXml(2,"A");
        FunctionalityXml h3 = new FunctionalityXml(3,"B");
        FunctionalityXml h4 = new FunctionalityXml(4,"C");
        FunctionalityXml h5 = new FunctionalityXml(5,"D");
        FunctionalityXml h6 = new FunctionalityXml(6,"E");

        util.addChildren(h1, -1);
        util.addChildren(h2, 1);
        util.addChildren(h3, 2);
        util.addChildren(h4, -1);
        util.addChildren(h5, 1);
        util.addChildren(h6, 2);

        System.out.println(util.getXml());

    }

}
