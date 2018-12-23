package com.github.dstaflund.nts.resource;

import com.github.dstaflund.nts.model.NtsMap;
import com.github.dstaflund.nts.model.SearchRequest;
import com.github.dstaflund.nts.provider.NtsMapProvider;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/nts")
@Produces(MediaType.APPLICATION_JSON)
public class NtsResource {

    @GET
    public List<NtsMap> GetMapDetails(@Valid @BeanParam SearchRequest request){
        return NtsMapProvider.getInstance().GetMaps(request);
    }
}
