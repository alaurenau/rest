package org.lavr.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.lavr.api.Vehicle;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Repository
public class ElasticsearchVehicleRepository implements VehicleRepository {

    private String transportAddress = "localhost";
    private Integer transportPort = 9300;
    private String elasticCluster = "cluster";
    private String elasticIndex = "index";
    private String elasticType = "vehicle";

    private Client elasticClient;
    private Gson gson;

    @PostConstruct
    public void init() {
        try {
            Settings settings = Settings.settingsBuilder().put("cluster.name", elasticCluster).build();
            elasticClient = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(transportAddress), transportPort));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        gson = new GsonBuilder().create();
    }

    @Override
    public List<Vehicle> getAll() {
        return null;
    }

    @Override
    public Vehicle getByVin(String vin) {
        Vehicle vehicle = null;
        try {
            vehicle = gson.fromJson(elasticClient.prepareGet(elasticIndex, elasticType, vin).get().getSourceAsString(),
                    Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    @Override
    public void insert(Vehicle vehicle) {
        try {
            elasticClient.prepareIndex(elasticIndex, elasticType)
                    .setSource(gson.toJson(vehicle)).setId(vehicle.getVin()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        insert(vehicle);
    }

    @Override
    public void delete(Vehicle vehicle) {
        try {
            elasticClient.prepareDelete(elasticIndex, elasticType, vehicle.getVin()).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTransportAddress() {
        return transportAddress;
    }

    public void setTransportAddress(String transportAddress) {
        this.transportAddress = transportAddress;
    }

    public Integer getTransportPort() {
        return transportPort;
    }

    public void setTransportPort(Integer transportPort) {
        this.transportPort = transportPort;
    }

    public String getElasticCluster() {
        return elasticCluster;
    }

    public void setElasticCluster(String elasticCluster) {
        this.elasticCluster = elasticCluster;
    }

    public String getElasticIndex() {
        return elasticIndex;
    }

    public void setElasticIndex(String elasticIndex) {
        this.elasticIndex = elasticIndex;
    }

    public String getElasticType() {
        return elasticType;
    }

    public void setElasticType(String elasticType) {
        this.elasticType = elasticType;
    }

    public Client getElasticClient() {
        return elasticClient;
    }

    public void setElasticClient(Client elasticClient) {
        this.elasticClient = elasticClient;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
