/**
 *

package kaica_lib_system;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.UUID;

public class OffsetLimitRequest extends PageRequest implements Pageable, Serializable {
    private static final UUID uuid = UUID.randomUUID();

    private int limit;
    private int offset;
    private Sort sort;

    public OffsetLimitRequest(int offset, int limit, Sort sort){
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }
}

 */