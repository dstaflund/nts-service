package com.github.dstaflund.nts;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/nts")
@Produces(MediaType.APPLICATION_JSON)
public class NtsService {

    @GET
    @Valid
    public SearchRequest GetMapDetails(@BeanParam SearchRequest request){
        return request;
    }
}
