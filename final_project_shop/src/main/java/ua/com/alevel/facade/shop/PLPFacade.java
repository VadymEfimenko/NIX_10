package ua.com.alevel.facade.shop;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.entity.shop.Release;
import ua.com.alevel.view.dto.response.shop.ReleasePLPDto;
import ua.com.alevel.view.dto.response.util.PageData;

import java.util.List;

public interface PLPFacade {

    PageData<ReleasePLPDto> search(WebRequest webRequest);

    List<String> searchReleaseName(String query);

    List<Long> addToCart(Long id);

    List<Release> getCart();

    void setCart(List<Long> cart);

    List<Long> getCartId();
}
