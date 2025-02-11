package app.modules.router;

public interface IRoute {
    void render();
    void execute(String args) throws Exception;
}
