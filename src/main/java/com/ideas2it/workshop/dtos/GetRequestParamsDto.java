package com.ideas2it.workshop.dtos;

import com.ideas2it.workshop.constants.ApiSpec;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.web.bind.annotation.RequestParam;

@ParameterObject
@Getter
@Setter
public class GetRequestParamsDto {
    @Parameter (description = ApiSpec.LIMIT_DESCRIPTION)
    public int limit;
    @Parameter (description = ApiSpec.PAGE_DESCRIPTION)
    public int page;
    @Parameter (description = ApiSpec.SORT_DESCRIPTION)
    public String sort;
}
