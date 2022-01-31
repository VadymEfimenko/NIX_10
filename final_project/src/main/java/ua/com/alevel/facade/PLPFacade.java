package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ReleasePLPDto;

import java.util.List;

public interface PLPFacade {

    PageData<ReleasePLPDto> search(WebRequest webRequest);
    List<String> searchReleaseName(String query);
    List<Long> addToCart(Long id);
    List<Release> getCart();
    void setCart(List<Long> cart);
    List<Long> getCartId();
}
