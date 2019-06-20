package judge.config;

import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.HttpOperation;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.ocpsoft.rewrite.servlet.http.event.HttpServletRewrite;

import javax.servlet.ServletContext;

@RewriteConfiguration
public class RoutingConfiguration extends HttpConfigurationProvider {

    @Override
    public Configuration getConfiguration(ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/logout")))
                .perform(new HttpOperation() {
                    @Override
                    public void performHttp(HttpServletRewrite event, EvaluationContext context) {
                        event.getRequest().getSession().invalidate();
                    }
                }.and(Redirect.temporary(context.getContextPath() + "/")))
                .addRule(Join.path("/").to("/faces/views/index.xhtml"))
                .addRule(Join.path("/register").to("/faces/views/user/register.xhtml"))
                .addRule(Join.path("/login").to("/faces/views/user/login.xhtml"))
                .addRule(Join.path("/problems/create").to("/faces/views/problem/create.xhtml"))
                .addRule(Join.path("/problems/details/{id}").to("/faces/views/problem/details.xhtml"))
                .addRule(Join.path("/problems/submit/{id}").to("/faces/views/submission/submit.xhtml"))
                .addRule(Join.path("/submissions/details/{id}").to("/faces/views/submission/details.xhtml"));
    }

    @Override
    public int priority() {
        return 10;
    }
}
