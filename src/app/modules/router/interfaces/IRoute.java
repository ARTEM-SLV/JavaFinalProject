package app.modules.router.interfaces;

public interface IRoute {
    void render();
    void execute(String args) throws Exception;
}
