package com.karol.model;

public enum SceneCode {
    MAIN_SCENE("/views/main.fxml", "/styles/main_scene.css"),
    ORDER_LIST_SCENE("/views/orderList.fxml", "/styles/orderList.css"),
    CREATE_ORDER_SCENE("/views/create_order.fxml", null);

    private String fxmlResourceUri;
    private String cssResourceUri;
    SceneCode(String fxmlResourceUri, String cssResourceUri){
        this.fxmlResourceUri = fxmlResourceUri;
        this.cssResourceUri = cssResourceUri;
    }
    public String getFxmlResourceUri(){
        return this.fxmlResourceUri;
    }
    public String getCssResourceUri(){
        return this.cssResourceUri;
    }
}
