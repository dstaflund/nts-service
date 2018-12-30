package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.NtsMap;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/nts/by/name")
@Produces(MediaType.APPLICATION_JSON)
public class NameSearchService {

    @GET
    public List<NtsMap> findMapsByName(@Valid @BeanParam NameSearchParams ctx){
        return NameSearchProvider.findMapsByName(ctx);
    }
}
