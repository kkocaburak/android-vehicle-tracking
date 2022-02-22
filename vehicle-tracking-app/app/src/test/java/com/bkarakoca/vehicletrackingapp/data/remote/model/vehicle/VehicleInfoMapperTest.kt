package com.bkarakoca.vehicletrackingapp.data.remote.model.vehicle

import com.bkarakoca.vehicletrackingapp.R
import com.google.android.gms.maps.model.LatLng
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class VehicleInfoMapperTest {

    private lateinit var mapper: VehicleInfoMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mapper = VehicleInfoMapper()
    }


    @Test
    fun `when toUIModel called should return list with descending order`() {
        // when
        val uiModelList = mapper.toUIModel(mockResponse)

        // then
        Assert.assertTrue(uiModelList[0].id == "3")
        Assert.assertTrue(uiModelList[1].id == "2")
        Assert.assertTrue(uiModelList[2].id == "1")
    }

    @Test
    fun `when toUIModel called should map drawable for fleet type`() {
        // when
        val uiModelList = mapper.toUIModel(mockResponse)

        // then
        Assert.assertTrue(uiModelList[0].drawableRes == R.drawable.ic_default_vehicle)
        Assert.assertTrue(uiModelList[1].drawableRes == R.drawable.ic_pooling)
        Assert.assertTrue(uiModelList[2].drawableRes == R.drawable.ic_taxi)
    }

    @Test
    fun `when toUIModel called should map long id to string`() {
        // when
        val uiModelList = mapper.toUIModel(mockResponse)

        // then
        Assert.assertTrue(uiModelList[0].id is String)
    }

    @Test
    fun `when toUIModel called should map LocationModel to LatLng`() {
        // when
        val uiModelList = mapper.toUIModel(mockResponse)

        // then
        Assert.assertTrue(uiModelList[0].location is LatLng)
    }

    private val mockResponse = VehicleInfoResponseModel(
        listOf(
            VehicleInfoModel(
                id = 1,
                coordinate = LocationModel(1.0, 1.0),
                fleetType = "TAXI",
                heading = 1.0
            ),
            VehicleInfoModel(
                id = 2,
                coordinate = LocationModel(2.0, 2.0),
                fleetType = "POOLING",
                heading = 1.0
            ),
            VehicleInfoModel(
                id = 3,
                coordinate = LocationModel(3.0, 3.0),
                fleetType = "UNIQUE",
                heading = 1.0
            ),
        )
    )

}