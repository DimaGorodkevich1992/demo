package com.example.demo;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.data.domain.Pageable;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Arrays.asList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Value("${spring.application.name}")
    private String title;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework")))
                .build();
    }

    @Bean
    public AlternateTypeRuleConvention pageableConvention(final TypeResolver resolver) {
        return new AlternateTypeRuleConvention() {

            @Override
            public int getOrder() {
                return Ordered.HIGHEST_PRECEDENCE;
            }

            @Override
            public List<AlternateTypeRule> rules() {
                return List.of(newRule(resolver.resolve(Pageable.class), resolver.resolve(pageableMixin())));
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title).build();
    }

    private Type pageableMixin() {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(
                        String.format("%s.generated.%s",
                                Pageable.class.getPackage().getName(),
                                Pageable.class.getSimpleName()))
                .withProperties(asList(
                        property(Integer.class, "page"),
                        property(Integer.class, "size"),
                        property(String.class, "sort")
                )).build();
    }

    private AlternateTypePropertyBuilder property(final Class<?> type, final String name) {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true);
    }
}
