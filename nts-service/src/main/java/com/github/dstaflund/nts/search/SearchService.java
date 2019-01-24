package com.github.dstaflund.nts.search;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.PagedResponse;
import com.github.dstaflund.nts.PagingParams;

import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/nts")
@Produces(MediaType.APPLICATION_JSON)
public class SearchService {

    @GET
    public PagedResponse<List<NtsMap>> findMaps(
        @Valid @BeanParam PagingParams paging,
        @Valid @BeanParam SearchParams req
    ){
        return SearchProvider.findMaps(paging, req);
    }
}
