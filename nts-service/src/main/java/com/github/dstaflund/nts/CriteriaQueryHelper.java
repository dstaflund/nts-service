package com.github.dstaflund.nts;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public final class CriteriaQueryHelper {

    private CriteriaQueryHelper(){
    }

    public static Order getOrderDir(CriteriaBuilder b, Root<NtsMap> root, PagingParams params){
        Expression e = root.get(getSortField(b, root, params));

        switch(params.getSortOrder()) {
            case -1:
                return b.desc(e);

            case 0:
                return b.asc(e);

            default:
                return b.asc(e);
        }
    }

    public static SingularAttribute<NtsMap, ?> getSortField(CriteriaBuilder b, Root<NtsMap> root, PagingParams params){
        switch(params.getSortField()){
            case "name":
                return NtsMap_.name;

            case "snippet":
                return NtsMap_.snippet;

            case "parent":
                return NtsMap_.parent;

            case "north":
                return NtsMap_.north;

            case "south":
                return NtsMap_.south;

            case "east":
                return NtsMap_.east;

            case "west":
                return NtsMap_.west;

            default:
                return NtsMap_.name;
        }
    }
}
