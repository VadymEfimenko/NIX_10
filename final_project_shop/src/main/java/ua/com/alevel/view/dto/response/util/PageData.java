package ua.com.alevel.view.dto.response.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class PageData<RES extends ResponseDto> {

    private int currentPage;
    private int pageSize;
    private int totalPageSize;
    private long itemsSize;
    private List<RES> items;
    private final int[] pageSizeItems;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private String sort;
    private String order;
    private long currentShowFromEntries;
    private long currentShowToEntries;

    public PageData() {
        this.currentPage = 0;
        this.pageSizeItems = new int[]{2,8, 16, 20};
        this.pageSize = this.pageSizeItems[0];
        this.totalPageSize = 0;
        this.items = new ArrayList<>();
        this.itemsSize = 0;
        this.showFirst = false;
        this.showPrevious = false;
        this.showNext = false;
        this.showLast = false;
    }

    public void initPaginationState() {
        if (pageSize < itemsSize) {
            if (itemsSize % pageSize == 0) {
                this.totalPageSize = (int) itemsSize / pageSize;
            } else {
                this.totalPageSize = (int) itemsSize / pageSize + 1;
            }
            this.showFirst = currentPage != 1;
            this.showPrevious = currentPage - 1 != 0;
            this.showLast = currentPage != totalPageSize;
            this.showNext = currentPage != totalPageSize;

            if (itemsSize != 0) {
                currentShowFromEntries = ((long) (currentPage - 1) * pageSize) + 1;
                int showTo = ((currentPage) * pageSize);
                if (showTo > itemsSize) {
                    currentShowToEntries = itemsSize;
                } else {
                    currentShowToEntries = showTo;
                }
            } else {
                currentShowToEntries = 0;
                currentShowFromEntries = 0;

            }
        } else if (itemsSize != 0) {
            currentShowToEntries = itemsSize;
            currentShowFromEntries = 1;
        } else {
            currentShowToEntries = 0;
            currentShowFromEntries = 0;
        }
    }

    @Override
    public String toString() {
        return "PageData{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalPageSize=" + totalPageSize +
                ", itemsSize=" + itemsSize +
                ", pageSizeItems=" + Arrays.toString(pageSizeItems) +
                ", showFirst=" + showFirst +
                ", showPrevious=" + showPrevious +
                ", showNext=" + showNext +
                ", showLast=" + showLast +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", currentShowFromEntries=" + currentShowFromEntries +
                ", currentShowToEntries=" + currentShowToEntries +
                '}';
    }
}
