package ua.hudyma.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanySpecialtyType implements LabeledEnum{
    ARTIFICIAL_INTELLIGENCE_DEVELOPMENT("Artificial Intelligence development"),
    BUSINESS_INTELLIGENCE_SERVICES("Business Intelligence Services"),
    CLOUD_SERVICES("Cloud Services"),
    COMPUTER_VISION_DEVELOPMENT_SERVICES("Computer vision development services"),
    CUSTOM_SOFTWARE_DEVELOPMENT("Custom Software Development"),
    DATA_ANALYTICS_SERVICES("Data Analytics Services"),
    DIGITAL_TRANSFORMATION("Digital Transformation"),
    EMBEDDED_SOFTWARE_DEVELOPMENT("Embedded Software Development"),
    GENERATIVE_AI_CONSULTING("Generative AI consulting"),
    INTELLIGENT_PLATFORMS_DEVELOPMENT("Intelligent Platforms Development"),
    INTERNET_OF_THINGS_DEVELOPMENT("Internet of Things Development"),
    MACHINE_LEARNING_DEVELOPMENT("Machine Learning development"),
    PRODUCT_DISCOVERY("Product Discovery"),
    RPA_SERVICES("RPA Services"),
    SOFTWARE_ENGINEERING_SERVICES("Software Engineering Services"),
    SOFTWARE_PRODUCT_ENGINEERING("Software Product Engineering"),
    TECHNOLOGY_CONSULTING("Technology Consulting"),
    VIRTUAL_REALITY_SOLUTIONS("Virtual Reality Solutions"),
    XR_METAVERSE_SOLUTIONS("XR & Metaverse Solutions");

    private final String label;
}

