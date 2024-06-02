package com.example.msuser.dto.pagination;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageContainer<T> {
    T data;
    Integer pageCount;
    Long totalElements;
}
