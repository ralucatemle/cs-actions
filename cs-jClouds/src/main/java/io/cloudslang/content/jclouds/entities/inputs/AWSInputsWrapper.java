package io.cloudslang.content.jclouds.entities.inputs;

import io.cloudslang.content.httpclient.HttpClientInputs;
import io.cloudslang.content.jclouds.entities.aws.AmazonApiService;
import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.utils.InputsUtil;

/**
 * Created by Mihai Tusa.
 * 8/10/2016.
 */
public class AWSInputsWrapper {
    private HttpClientInputs httpClientInputs;
    private CommonInputs commonInputs;
    private CustomInputs customInputs;

    private String serviceEndpoint;
    private String apiService;
    private String requestUri;
    private String requestPayload;
    private String date;
    private String version;
    private String networkInterfaceId;
    private String deviceIndex;
    private String securityToken;

    private AWSInputsWrapper(AWSInputsWrapperBuilder builder) {
        this.httpClientInputs = builder.httpClientInputs;
        this.commonInputs = builder.commonInputs;
        this.customInputs = builder.customInputs;

        this.serviceEndpoint = builder.serviceEndpoint;
        this.apiService = builder.apiService;
        this.requestUri = builder.requestUri;
        this.requestPayload = builder.requestPayload;
        this.date = builder.date;
        this.version = builder.version;
        this.networkInterfaceId = builder.networkInterfaceId;
        this.deviceIndex = builder.deviceIndex;
        this.securityToken = builder.securityToken;
    }

    public HttpClientInputs getHttpClientInputs() {
        return httpClientInputs;
    }

    public CommonInputs getCommonInputs() {
        return commonInputs;
    }

    public CustomInputs getCustomInputs() {
        return customInputs;
    }

    public String getServiceEndpoint() {
        return serviceEndpoint;
    }

    public String getApiService() {
        return apiService;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public String getRequestPayload() {
        return requestPayload;
    }

    public String getDate() {
        return date;
    }

    public String getVersion() {
        return version;
    }

    public String getNetworkInterfaceId() {
        return networkInterfaceId;
    }

    public String getDeviceIndex() {
        return deviceIndex;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public static class AWSInputsWrapperBuilder {
        private HttpClientInputs httpClientInputs;
        private CommonInputs commonInputs;
        private CustomInputs customInputs;

        private String serviceEndpoint;
        private String apiService;
        private String requestUri;
        private String requestPayload;
        private String date;
        private String version;
        private String networkInterfaceId;
        private String deviceIndex;
        private String securityToken;

        public AWSInputsWrapper build() {
            return new AWSInputsWrapper(this);
        }

        public AWSInputsWrapperBuilder withHttpClientInputs(HttpClientInputs inputs) {
            httpClientInputs = inputs;
            return this;
        }

        public AWSInputsWrapperBuilder withCommonInputs(CommonInputs inputs) {
            commonInputs = inputs;
            return this;
        }

        public AWSInputsWrapperBuilder withCustomInputs(CustomInputs inputs) {
            customInputs = inputs;
            return this;
        }

        public AWSInputsWrapperBuilder withServiceEndpoint(String inputValue) {
            serviceEndpoint = AmazonApiService.getValue(inputValue) +
                    Constants.Miscellaneous.DOT + Constants.AWSParams.AMAZON_HOSTNAME;
            return this;
        }

        public AWSInputsWrapperBuilder withApiService(String inputValue) {
            apiService = AmazonApiService.getValue(inputValue);
            return this;
        }

        public AWSInputsWrapperBuilder withRequestUri(String inputValue) {
            requestUri = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.SCOPE_SEPARATOR);
            return this;
        }

        public AWSInputsWrapperBuilder withRequestPayload(String inputValue) {
            requestPayload = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.EMPTY);
            return this;
        }

        public AWSInputsWrapperBuilder withDate(String inputValue) {
            date = inputValue;
            return this;
        }

        public AWSInputsWrapperBuilder withNetworkInterfaceId(String inputValue) {
            networkInterfaceId = inputValue;
            return this;
        }

        public AWSInputsWrapperBuilder withDeviceIndex(String inputValue) {
            deviceIndex = inputValue;
            return this;
        }

        public AWSInputsWrapperBuilder withVersion(String inputValue) {
            version = inputValue;
            return this;
        }

        public AWSInputsWrapperBuilder withSecurityToken(String inputValue) {
            securityToken = inputValue;
            return this;
        }
    }
}