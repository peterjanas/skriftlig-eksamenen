package app.dtos;

import lombok.Getter;

@Getter
public class GuideTotalPriceDTO
{
    private int guideId;
    private int totalPrice;

    public GuideTotalPriceDTO(int guideId, int totalPrice) {
        this.guideId = guideId;
        this.totalPrice = totalPrice;
    }

}
