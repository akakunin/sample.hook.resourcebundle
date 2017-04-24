package ru.emdev.hook.resourcebundle;

import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

@Component(
		immediate = true,
		property = {
			"bundle.symbolic.name=com.liferay.social.activity.web",
			"resource.bundle.base.name=content.Language",
			"servlet.context.name=social-activity-web"
		}
	)
public class ResourceBundleLoaderComponent implements ResourceBundleLoader {

	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return _resourceBundleLoader.loadResourceBundle(languageId);
	}
	
	@Reference(target = "(&(bundle.symbolic.name=com.liferay.social.activity.web)(!(component.name=ru.emdev.hook.resourcebundle.ResourceBundleLoaderComponent)))")
	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			new CacheResourceBundleLoader(
				new ClassResourceBundleLoader(
					"content.Language",
					ResourceBundleLoaderComponent.class.getClassLoader())),
			resourceBundleLoader);
	}

	private AggregateResourceBundleLoader _resourceBundleLoader;

}
