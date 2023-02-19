package com.example.model.vehicle;

import com.example.helper.Indices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VehicleService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final RestHighLevelClient client;

    public Boolean save(final Vehicle vehicle) {
        try {
            final String vehicleAsString = MAPPER.writeValueAsString(vehicle);
            IndexRequest request = new IndexRequest(Indices.VEHICLE_INDEX);
            request.id(vehicle.getId());
            request.source(vehicleAsString, XContentType.JSON);
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            log.error(e.getMessage(), e);
        }
        return false ;
    }
    public Vehicle getId(final String id){
        try {
            final GetResponse documentFields=client.get(
                    new GetRequest(Indices.VEHICLE_INDEX,id),
                  RequestOptions.DEFAULT
            );
            if (documentFields==null||documentFields.isSourceEmpty()){
                return null;
            }
            return MAPPER.readValue(documentFields.getSourceAsString(),Vehicle.class);
        }catch (final Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
