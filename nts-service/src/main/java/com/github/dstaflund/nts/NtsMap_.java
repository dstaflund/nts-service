package com.github.dstaflund.nts;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel( NtsMap.class )
public class NtsMap_ {
    public static volatile SingularAttribute<NtsMap, String> name;
    public static volatile SingularAttribute<NtsMap, String> snippet;
    public static volatile SingularAttribute<NtsMap, Integer> parent;
    public static volatile SingularAttribute<NtsMap, Float> north;
    public static volatile SingularAttribute<NtsMap, Float> south;
    public static volatile SingularAttribute<NtsMap, Float> east;
    public static volatile SingularAttribute<NtsMap, Float> west;
    public static volatile SingularAttribute<NtsMap, String> searchName;
    public static volatile SingularAttribute<NtsMap, String> searchParent;
    public static volatile SingularAttribute<NtsMap, String> searchNorth;
    public static volatile SingularAttribute<NtsMap, String> searchSouth;
    public static volatile SingularAttribute<NtsMap, String> searchEast;
    public static volatile SingularAttribute<NtsMap, String> searchWest;
}
