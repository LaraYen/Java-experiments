import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMap {
    private final String url;

    //Используем потокобезопасный класс
    private final CopyOnWriteArrayList<SiteMap> siteMapChildrens;

    public SiteMap (String url){
        siteMapChildrens = new CopyOnWriteArrayList<>();
        this.url = url;
    }

    //Добавляем дочерние ссылки
    public void addChildren (SiteMap children){
        siteMapChildrens.add(children);
    }

    public CopyOnWriteArrayList<SiteMap> getSiteMapChildrens(){
        return siteMapChildrens;
    }

    public String getUrl() {
        return url;
    }
}
