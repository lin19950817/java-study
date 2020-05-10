package org.lzn.xml.collection;

import java.util.*;

/**
 * 集合注入
 *
 * @author LinZhenNan lin_hehe@qq.com 2020/05/10 17:45
 */
public class CollectionData {
    private String[] arrayData;
    private List<String> listData;
    private Set<String> setData;
    private Map<String, String> mapData;
    private Properties propertiesData;

    //
    // setter/getter
    // ------------------------------------------------------------------------------

    public String[] getArrayData() {
        return arrayData;
    }

    public void setArrayData(String[] arrayData) {
        this.arrayData = arrayData;
    }

    public List<String> getListData() {
        return listData;
    }

    public void setListData(List<String> listData) {
        this.listData = listData;
    }

    public Set<String> getSetData() {
        return setData;
    }

    public void setSetData(Set<String> setData) {
        this.setData = setData;
    }

    public Map<String, String> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, String> mapData) {
        this.mapData = mapData;
    }

    public Properties getPropertiesData() {
        return propertiesData;
    }

    public void setPropertiesData(Properties propertiesData) {
        this.propertiesData = propertiesData;
    }

    //
    // toString
    // ------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "CollectionData{" +
                "\n\tarrayData=" + Arrays.toString(arrayData) +
                ", \n\tlistData=" + listData +
                ", \n\tsetData=" + setData +
                ", \n\tmapData=" + mapData +
                ", \n\tpropertiesData=" + propertiesData +
                "\n}";
    }
}
