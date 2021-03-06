package org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.parsers;

import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.exceptions.PipelineAsYamlException;
import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.interfaces.ParserInterface;
import org.jenkinsci.plugins.workflow.multibranch.yaml.pipeline.models.EnvironmentModel;

import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * Parser for {@link EnvironmentModel}
 */
public class EnvironmentParser extends AbstractParser implements ParserInterface<EnvironmentModel> {

    private LinkedHashMap environmentNode;
    private LinkedHashMap parentNode;

    /**
     * @param parentNode Parent Node which contains model definition as yaml
     */
    public EnvironmentParser(LinkedHashMap parentNode){
        this.yamlNodeName = EnvironmentModel.directive;
        this.parentNode = parentNode;
    }

    @Override
    public Optional<EnvironmentModel> parse() {
        try {
            this.environmentNode = this.getChildNodeAsLinkedHashMap(parentNode);
            return Optional.of(new EnvironmentModel(this.convert(this.extractParameters(this.environmentNode))));
        }
        catch (PipelineAsYamlException p) {
            return Optional.empty();
        }
    }
}
