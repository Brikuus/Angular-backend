package org.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.greta.eshop_api.exposition.dtos.OrderItemRequestDTO;

import java.time.LocalDateTime;

@Entity
@Table(name="order_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private double unit_price;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;

    public Long getId() {
        return this.id;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public double getUnitPrice() {
        return this.unit_price;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void updateFrom(OrderItemRequestDTO dto) {
        this.quantity = dto.quantity();
        this.unit_price = dto.unit_price();
    }
}


