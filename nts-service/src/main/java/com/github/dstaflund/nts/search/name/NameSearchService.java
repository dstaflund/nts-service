package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.PagingData;
import com.github.dstaflund.nts.PagedResponse;

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
    public PagedResponse<List<NtsMap>> findMapsByName(
        @Valid @BeanParam PagingData paging,
        @Valid @BeanParam NameSearchParams req
    ){
        return NameSearchProvider.findMapsByName(paging, req);
    }
}
