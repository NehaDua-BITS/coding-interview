package org.interview.round1.service.impl;

import lombok.Getter;
import org.interview.round1.exceptions.ErrorCodes;
import org.interview.round1.exceptions.InvalidInputException;
import org.interview.round1.service.Router;

import java.util.HashMap;
import java.util.Map;

@Getter
public class DefaultRouter implements Router {

    private Map<String, String> apiMethodMap;

    public DefaultRouter() {
        this.apiMethodMap = new HashMap<>();
    }

    @Override
    public void addRoute(String path, String methodName) {
        if (apiMethodMap.containsKey(path)) {
            throw new InvalidInputException("Path is already registered!");
        }
        apiMethodMap.put(path, methodName);
    }

    @Override
    public String route(String path) {
        String targetMethod = getMethod(path);
        if (targetMethod == null) {
            throw new InvalidInputException(ErrorCodes.ROUTE_NOT_FOUND);
        }
        return targetMethod;
    }

    private String getMethod(String path) {
        String targetMethod = apiMethodMap.get(path);
        if (targetMethod != null) {
            return targetMethod;
        }

        //regex check
        for (Map.Entry<String, String> pathMethodEntry : apiMethodMap.entrySet()) {

            String[] urlComponents = pathMethodEntry.getKey().split("/");
            String[] requestedPathComponents = path.split("/");

            if (urlComponents.length != requestedPathComponents.length) {
                continue;
            };

            boolean isMatched = true;
            int length = urlComponents.length;
            for (int i=0 ; i < length; i++) {
                String component = urlComponents[i];
                if (!component.equals(requestedPathComponents[i]) &&
                        !(component.charAt(0) == '{' && component.charAt(component.length()-1) == '}')) { //regex is present
                    isMatched = false;
                    break;
                }
            }

            if (isMatched) {
                return pathMethodEntry.getValue();
            }
        }

        return null;
    }
}
