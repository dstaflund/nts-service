package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.search.NtsMap;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/nts/by/area")
@Produces(MediaType.APPLICATION_JSON)
public class AreaSearchService {

    @GET
    public List<NtsMap> findMapsByArea(@Valid @BeanParam AreaSearchParams ctx){
        return AreaSearchProvider.getInstance().findMapsByArea(ctx);
    }
}
