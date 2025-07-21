package com.example.demo.dto.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductOptionRequestDto(

    @NotBlank(message = "상품 이름은 공백일 수 없습니다.")
    @Size(max = 50, message = "상픔의 이름은 공백 포함 15자 이하로 입력해주세요.")
    @Pattern(
        regexp = "^[a-zA-Z0-9가-힣 ()\\[\\]+\\-&/_]*$",
        message = "상품 이름에는 (), [], +, -, &, /, _ 외 특수문자는 사용할 수 없습니다."
    )
    String name,

    @Min(value = 1, message = "옵선 수량은 최대 1 이상이어야 합니다.")
    @Max(value = 99999999, message = "옵션 수량은 최대 1억 미만 이어야 합니다.")
    int quantity
) {
}
