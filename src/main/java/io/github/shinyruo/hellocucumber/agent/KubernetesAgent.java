package io.github.shinyruo.hellocucumber.agent;

import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;
import java.util.Properties;

import static java.util.concurrent.TimeUnit.MINUTES;

@Log4j2
public class KubernetesAgent extends AbstractAgent {

    private final KubernetesClient client;

    public KubernetesAgent(String agentName, Properties properties) {
        super(agentName, properties);
        this.client = new KubernetesClientBuilder().build();
    }

    public void createNamespace(String namespace) {
        log.info("Creating namespace: {}", namespace);
        try {
            client.resource(new NamespaceBuilder()
                    .withNewMetadata()
                    .withName(namespace)
                    .endMetadata()
                    .build()).create();

            client.namespaces().withName(namespace).waitUntilCondition(
                    ns -> ns != null && ns.getMetadata().getName().equals(namespace),
                    5, MINUTES
            );

            log.info("Namespace created: {}", namespace);
        } catch (Exception e) {
            log.error("Failed to create namespace: {}", namespace, e);
        }
    }

    public void deleteNamespace(String namespace) {
        log.info("Deleting namespace: {}", namespace);
        try {
            client.namespaces().withName(namespace).delete();
            client.namespaces().withName(namespace).waitUntilCondition(
                    Objects::isNull,
                    5, MINUTES
            );
            log.info("Namespace deleted: {}", namespace);
        } catch (Exception e) {
            log.error("Failed to delete namespace: {}", namespace, e);
        }
    }

    public void createPod(String namespace, Pod pod) {
        client.pods().inNamespace(namespace).resource(pod).create();
    }

    public void deletePod(String namespace, String podName) {
        client.pods().inNamespace(namespace).withName(podName).delete();
    }

}