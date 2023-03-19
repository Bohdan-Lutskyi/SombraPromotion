package com.sombra.promotion.util;

import com.sombra.promotion.PromotionApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Transactional
@AutoConfigureMockMvc
//@AutoConfigureEmbeddedDatabase
@SpringBootTest(classes = {PromotionApplication.class, TestUtil.class})
@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "admin", authorities = { "ADMIN" })
public @interface ApiTestConfiguration {
}
