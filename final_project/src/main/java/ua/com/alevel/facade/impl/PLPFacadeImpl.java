package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.BadRequestException;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.release.Release;
import ua.com.alevel.service.ElasticReleaseSearchService;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.service.shop.ReleaseService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ReleasePLPDto;

import java.util.*;

@Service
public class PLPFacadeImpl implements PLPFacade {
    private final PLPService plpService;
    private final ElasticReleaseSearchService elasticReleaseSearchService;
    private List<Long> cart = new ArrayList<>();
    private final ReleaseService releaseService;

    public PLPFacadeImpl(PLPService plpService, ElasticReleaseSearchService elasticReleaseSearchService, ReleaseService releaseService) {
        this.plpService = plpService;
        this.elasticReleaseSearchService = elasticReleaseSearchService;
        this.releaseService = releaseService;
    }

    @Override
    public PageData<ReleasePLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(webRequest);
        if (webRequest.getParameterMap().get(WebRequestUtil.MUSICIAN_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebRequestUtil.MUSICIAN_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            String[] musicianNames = params[0].split(",");
            List<String> musicians = Arrays.asList(musicianNames);
            queryMap.put(WebRequestUtil.MUSICIAN_PARAM, musicians);
        }
        if (webRequest.getParameterMap().get(WebRequestUtil.SEARCH_RELEASE_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebRequestUtil.SEARCH_RELEASE_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            String searchRelease = params[0];
            queryMap.put(WebRequestUtil.SEARCH_RELEASE_PARAM, searchRelease);
        }
        DataTableResponse<Release> releases = plpService.search(queryMap, dataTableRequest);
        List<ReleasePLPDto> releasePLPDtos = releases.getItems().stream().map(ReleasePLPDto::new).toList();
        PageData<ReleasePLPDto> pageData = (PageData<ReleasePLPDto>) WebResponseUtil.initPageData(releases);
        pageData.setItems(releasePLPDtos);
        return pageData;
    }

    @Override
    public List<String> searchReleaseName(String query) {
        return elasticReleaseSearchService.searchReleaseName(query);
    }

    @Override
    public List<Long> addToCart(Long id) {
        cart.add(id);
        return cart;
    }

    @Override
    public List<Release> getCart() {
        List<Release> releases = new ArrayList<>();
        cart.stream().forEach(id -> releases.add(releaseService.findById(id).get()));
        return releases;
    }

    public void setCart(List<Long> cart) {
        this.cart = cart;
    }

    @Override
    public List<Long> getCartId() {
        return cart;
    }
}
