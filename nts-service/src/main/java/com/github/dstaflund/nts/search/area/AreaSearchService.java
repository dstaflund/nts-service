package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.PagingParams;
import com.github.dstaflund.nts.PagedResponse;

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
    public PagedResponse<List<NtsMap>> findMapsByArea(
        @Valid @BeanParam PagingParams paging,
        @Valid @BeanParam AreaSearchParams req
    ){
        return AreaSearchProvider.findMapsByArea(paging, req);
    }
}
