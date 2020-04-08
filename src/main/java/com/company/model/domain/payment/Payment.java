package com.company.model.domain.payment;

import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Payment {

    @Pattern(regexp = "\\d{4}", message = "Please, enter correct number of card (4 digits)")
    private String numberCard;
    private Long idTourPackage;

    public Payment() {
    }

    public Payment(String numberCard, Long idTourPackage) {
        this.numberCard = numberCard;
        this.idTourPackage = idTourPackage;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Long getIdTourPackage() {
        return idTourPackage;
    }

    public void setIdTourPackage(Long idTourPackage) {
        this.idTourPackage = idTourPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return numberCard == payment.numberCard &&
                Objects.equals(idTourPackage, payment.idTourPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberCard, idTourPackage);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "numberCards=" + numberCard +
                ", idTourPackage=" + idTourPackage +
                '}';
    }
}
