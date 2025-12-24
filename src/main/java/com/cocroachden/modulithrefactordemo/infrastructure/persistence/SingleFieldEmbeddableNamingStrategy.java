package com.cocroachden.modulithrefactordemo.infrastructure.persistence;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;

import java.lang.reflect.Field;

public class SingleFieldEmbeddableNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        // Check if this is an attribute of an embedded object
        if (source.getAttributePath().getParent() != null) {
            String embeddingPropertyName = source.getAttributePath().getParent().getProperty();
            Class<?> embeddableClass = source.getAttributePath().getParent().getClass();
            
            // Check if the embeddable has only one field
            if (isSingleFieldEmbeddable(embeddableClass)) {
                // Use the embedding field name instead of the embedded field name
                return toIdentifier(embeddingPropertyName, source.getBuildingContext());
            }
        }
        
        return super.determineBasicColumnName(source);
    }
    
    private boolean isSingleFieldEmbeddable(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        
        Field[] declaredFields = clazz.getDeclaredFields();
        int persistentFieldCount = 0;
        
        for (Field field : declaredFields) {
            // Count only non-static, non-transient fields
            int modifiers = field.getModifiers();
            if (!java.lang.reflect.Modifier.isStatic(modifiers) && 
                !java.lang.reflect.Modifier.isTransient(modifiers)) {
                persistentFieldCount++;
            }
        }
        
        return persistentFieldCount == 1;
    }
}
