package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.search.NtsMap;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/nts/by/coord")
@Produces(MediaType.APPLICATION_JSON)
public class CoordinateSearchService {

    @GET
    public List<NtsMap> findMapsByCoordinate(@Valid @BeanParam CoordinateSearchParams ctx){
        return CoordinateSearchProvider.getInstance().findMapsByCoordinate(ctx);
    }
}
