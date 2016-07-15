package io.cloudslang.content.xml.actions;

import com.hp.oo.sdk.content.annotations.Action;
import com.hp.oo.sdk.content.annotations.Output;
import com.hp.oo.sdk.content.annotations.Param;
import com.hp.oo.sdk.content.annotations.Response;
import com.hp.oo.sdk.content.plugin.ActionMetadata.MatchType;
import io.cloudslang.content.xml.entities.Constants;
import io.cloudslang.content.xml.entities.inputs.CommonInputs;
import io.cloudslang.content.xml.entities.inputs.CustomInputs;
import io.cloudslang.content.xml.services.RemoveService;
import io.cloudslang.content.xml.utils.ResultUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by markowis on 23/02/2016.
 */
public class Remove {
    /**
     * Removes an element or attribute from an XML document.
     *
     * @param xmlDocument       XML string to remove element or attribute from
     * @param xmlDocumentSource The source type of the xml document.
     *                          Valid values: xmlString, xmlPath
     *                          Default value: xmlString
     * @param xPathQuery        XPATH query that results in an element or element list to remove or the element or
     *                          element list containing the attribute to remove
     * @param attributeName     optional - name of attribute to remove if removing an attribute; leave empty if removing
     *                          an element
     * @param secureProcessing  optional - whether to use secure processing
     * @return map of results containing success or failure text, a result message, and the modified XML
     */
    @Action(name = "Remove",
            outputs = {
                    @Output(Constants.Outputs.RETURN_CODE),
                    @Output(Constants.Outputs.RETURN_RESULT),
                    @Output(Constants.Outputs.RESULT_XML),
                    @Output(Constants.Outputs.ERROR_MESSAGE)},
            responses = {
                    @Response(text = Constants.ResponseNames.SUCCESS, field = Constants.Outputs.RETURN_CODE, value = Constants.ReturnCodes.SUCCESS, matchType = MatchType.COMPARE_EQUAL),
                    @Response(text = Constants.ResponseNames.FAILURE, field = Constants.Outputs.RETURN_CODE, value = Constants.ReturnCodes.FAILURE, matchType = MatchType.COMPARE_EQUAL, isDefault = true, isOnFail = true)})
    public Map<String, String> execute(
            @Param(value = Constants.Inputs.XML_DOCUMENT, required = true) String xmlDocument,
            @Param(value = Constants.Inputs.XML_DOCUMENT_SOURCE) String xmlDocumentSource,
            @Param(value = Constants.Inputs.XPATH_ELEMENT_QUERY, required = true) String xPathQuery,
            @Param(value = Constants.Inputs.ATTRIBUTE_NAME) String attributeName,
            @Param(value = Constants.Inputs.SECURE_PROCESSING) String secureProcessing) {

        Map<String, String> result = new HashMap<>();
        try {
            CommonInputs inputs = new CommonInputs.CommonInputsBuilder()
                    .withXmlDocument(xmlDocument)
                    .withXmlDocumentSource(xmlDocumentSource)
                    .withXpathQuery(xPathQuery)
                    .withSecureProcessing(secureProcessing)
                    .build();

            CustomInputs customInputs = new CustomInputs.CustomInputsBuilder()
                    .withAttributeName(attributeName)
                    .build();

            result =  new RemoveService().execute(inputs, customInputs);
        } catch (Exception e) {
            ResultUtils.populateFailureResult(result, Constants.ErrorMessages.PARSING_ERROR + e.getMessage());
        }
        return result;
    }
}