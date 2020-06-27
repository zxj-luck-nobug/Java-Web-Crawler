package com.crawler.config.anno;

import javax.annotation.PostConstruct;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public class AnnoTest extends AbstractProcessor {
    @InjectionParam(value = "hello chen")
    public String anno;

    public String getAnno() {
        return anno;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    /**
     * {@inheritDoc}
     *
     * @param annotations
     * @param roundEnv
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
