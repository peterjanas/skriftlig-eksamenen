package app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public class PackingItemDTO
{
    @JsonProperty("name")
    private String name;

    @JsonProperty("weightInGrams")
    private int weightInGrams;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @JsonProperty("createdAt")
    private ZonedDateTime createdAt;

    @JsonProperty("updatedAt")
    private ZonedDateTime updatedAt;

    @JsonProperty("buyingOptions")
    private List<BuyingOption> buyingOptions;


    public static class BuyingOption
    {
        @JsonProperty("shopName")
        private String shopName;

        @JsonProperty("shopUrl")
        private String shopUrl;

        @JsonProperty("price")
        private double price;
    }
}
