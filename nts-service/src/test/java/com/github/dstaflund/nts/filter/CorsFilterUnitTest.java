package com.github.dstaflund.nts.filter;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(JMockit.class)
public class CorsFilterUnitTest {
    @Tested CorsFilter mFilter;
    @Mocked ContainerRequestContext mReq;
    @Mocked ContainerResponseContext mRes;
    @Mocked MultivaluedMap<String, Object> mHeaders;
    @Mocked LogManager mLogManager;
    @Mocked Logger mLogger;

    @Test
    public void isJaxRsProvider() {
        assertNotNull(CorsFilter.class.getAnnotation(Provider.class));
    }

    @Test
    public void shouldAllowAllOrigins() throws IOException {
        new Expectations(){{
            mRes.getHeaders(); result = mHeaders;
        }};
        mFilter.filter(mReq, mRes);
        new Verifications(){{
            mHeaders.add("Access-Control-Allow-Origin", "*");
        }};
    }

    @Test
    public void shouldAllowApprovedHeaders() throws IOException {
        new Expectations(){{
            mRes.getHeaders(); result = mHeaders;
        }};
        mFilter.filter(mReq, mRes);
        new Verifications(){{
            mHeaders.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        }};
    }

    @Test
    public void shouldAllowApprovedMethods() throws IOException {
        new Expectations(){{
            mRes.getHeaders(); result = mHeaders;
        }};
        mFilter.filter(mReq, mRes);
        new Verifications(){{
            mHeaders.add("Access-Control-Allow-Methods", "GET, OPTIONS");
        }};
    }
}
