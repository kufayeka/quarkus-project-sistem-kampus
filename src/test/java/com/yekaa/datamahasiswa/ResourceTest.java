package com.yekaa.datamahasiswa;

import com.yekaa.modules.datamahasiswa.dto.DataMahasiswaResponseDTO;
import com.yekaa.modules.datamahasiswa.entity.DataMahasiswa;
import com.yekaa.modules.datamahasiswa.service.DataMahasiswaService;
import com.yekaa.modules.dataprs.entity.DataPrs;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ResourceTest {

    @InjectMock
    DataMahasiswaService dataMahasiswaService;


    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/api/v1/healthcheck")
                .then()
                .statusCode(200)
                .body(is("Hello yeKaa brrtatatata"));
    }

    @Test
    public void testGetAllDataMahasiswa() {
        List<DataMahasiswaResponseDTO> getAllResponse =
                dataMahasiswaService.getAllDataMahasiswa();

        given()
                .when().get("/api/v1/getAllDataMahasiswa")
                .then()
                .statusCode(200)
                .body(is(getAllResponse));
    }


}
