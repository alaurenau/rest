package org.lavr.repository;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.net.InetAddress;
import java.net.UnknownHostException;

@ContextConfiguration(locations = {"classpath:test-context.xml"})
public abstract class AbstractIntegrationTest extends AbstractTestNGSpringContextTests {

    private String transportAddress = "localhost";
    private Integer transportPort = 9307;
    private String elasticCluster = "elasticsearch";
    private String elasticIndex = "index";
    private String elasticType = "vehicle";

    private Client elasticClient;


    @BeforeClass
    public void setUp() throws Exception {
        try {
            Settings settings = Settings.builder().put("cluster.name", elasticCluster).build();
            elasticClient = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(transportAddress), transportPort));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (elasticClient.admin().indices().prepareExists(elasticIndex).get().isExists()) {
            elasticClient.admin().indices().prepareDelete(elasticIndex).get();
        }
    }
}

