import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class SiteMapRecursiveAction extends RecursiveAction {
    private final SiteMap siteMap;

    //Используем потокобезопасный класс
    private final static CopyOnWriteArrayList linksPool = new CopyOnWriteArrayList();

    public SiteMapRecursiveAction (SiteMap siteMap){
        this.siteMap = siteMap;
    }

    @Override
    protected void compute() {
        linksPool.add(siteMap.getUrl());

        ConcurrentSkipListSet<String> links = HTMLparse.getLinks(siteMap.getUrl());
        for (String link : links){
            if (!linksPool.contains(link)){
                linksPool.add(link);
                siteMap.addChildren(new SiteMap(link));
            }
        }

        List<SiteMapRecursiveAction> taskList = new ArrayList<>();
        for (SiteMap child : siteMap.getSiteMapChildrens()){
            SiteMapRecursiveAction task = new SiteMapRecursiveAction(child);
            task.fork();
            taskList.add(task);
        }

        for (SiteMapRecursiveAction task : taskList) {
            task.join();
        }

    }
}
