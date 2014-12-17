package com.bol.cd.stash.model;

import java.io.Serializable;

public class RepositoryHookDetails implements Serializable {
    private static final long serialVersionUID = -847498479168490802L;
    public String key;
    public String name;
    public String type;
    public String description;
    public String version;
    public String configFormKey;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getConfigFormKey() {
        return configFormKey;
    }

    public void setConfigFormKey(String configFormKey) {
        this.configFormKey = configFormKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryHookDetails that = (RepositoryHookDetails) o;

        if (!configFormKey.equals(that.configFormKey)) return false;
        if (!key.equals(that.key)) return false;
        if (!type.equals(that.type)) return false;
        if (!version.equals(that.version)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + configFormKey.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RepositoryHookDetails{");
        sb.append("key='").append(key).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", configFormKey='").append(configFormKey).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
