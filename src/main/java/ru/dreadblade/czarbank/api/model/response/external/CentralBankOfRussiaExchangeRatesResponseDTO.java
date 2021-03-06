package ru.dreadblade.czarbank.api.model.response.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import ru.dreadblade.czarbank.util.BigDecimalDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "ValCurs")
public class CentralBankOfRussiaExchangeRatesResponseDTO {

    @JacksonXmlProperty(localName = "Date", isAttribute = true)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JacksonXmlProperty(localName = "Valute")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ExchangeRateOnDateDTO> rates;

    public boolean isValid() {
        return rates != null && !rates.isEmpty() && date != null;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExchangeRateOnDateDTO {
        @JacksonXmlProperty(localName = "ID", isAttribute = true)
        private String uniqueCurrencyCode;

        @JacksonXmlProperty(localName = "CharCode")
        private String currencyCode;

        @JacksonXmlProperty(localName = "Nominal")
        private Long nominal;

        @JacksonXmlProperty(localName = "Value")
        @JsonDeserialize(using = BigDecimalDeserializer.class)
        private BigDecimal rate;
    }
}
