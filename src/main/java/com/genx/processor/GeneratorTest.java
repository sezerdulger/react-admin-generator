package com.genx.processor;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import com.genx.processor.annotation.DAO;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GeneratorTest extends AbstractProcessor {

	private Elements elements;

	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		elements = processingEnv.getElementUtils();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		for (Element element : roundEnv.getElementsAnnotatedWith(DAO.class)) {
			System.out
					.println("ss: element found: " + element.getClass().getSimpleName());
			TypeElement typeElement = (TypeElement) element;
			TypeMirror mirror = ((TypeElement) element).getSuperclass();
			
			DAO annotation = typeElement.getAnnotation(DAO.class);
			String qualifiedSuperClassName, simpleTypeName;
			
			try {
				typeElement.getEnclosedElements().forEach(e -> {
					processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
							String.format(e.getClass().getSimpleName(), ""), e);
				});
			      Class<?> clazz = ((PackageElement)typeElement.getEnclosingElement()).getClass();
			    		  qualifiedSuperClassName = clazz.getCanonicalName();
			      simpleTypeName = clazz.getSimpleName();
			      
			      
			    } catch (MirroredTypeException mte) {
			      DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
			      TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
			      qualifiedSuperClassName = classTypeElement.getQualifiedName().toString();
			      simpleTypeName = classTypeElement.getSimpleName().toString();
			    }

			PackageElement pacakgeElement = elements.getPackageOf(element);
			String packageName = pacakgeElement.getQualifiedName().toString();
			// String superClassName = ((DeclaredType) mirror).asElement().getSimpleName()
			// .toString();

			String controllerClassName = simpleTypeName
					+ "Controller";
			try {
				// Writer file = processingEnv.getFiler()
				// .createSourceFile(controllerClassName)
				// .openWriter();
				// Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
				// FileObject templateFile =
				// processingEnv.getFiler().getResource(StandardLocation.CLASS_OUTPUT, "",
				// "template/ControllerTemplate.txt");

				// File templateFile =
				// ResourceUtils.getFile("template/ControllerTemplate.txt");
				// String templateStr = String.join("\n", Files.readAllLines(.toPath()));

				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
						String.format(controllerClassName, ""), element);

				MethodSpec main = MethodSpec.methodBuilder("main")
						.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
						.returns(void.class).addParameter(String[].class, "args")
						.addStatement("$T.out.println($S)", System.class,
								"Hello, JavaPoet!")
						.build();

				TypeSpec helloWorld = TypeSpec.classBuilder(controllerClassName)
						.addModifiers(Modifier.PUBLIC, Modifier.FINAL).addMethod(main)
						.build();

				JavaFile javaFile = JavaFile.builder("com.genx.base.entity", helloWorld)
						.build();

				javaFile.writeTo(processingEnv.getFiler());

				// file.write(String.format(templateFile.getCharContent(true).toString(),
				// packageName, element.getClass().getSimpleName() + "Controller"));
				// file.flush();
				// file.close();
			}
			catch (IOException ex) {
				processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
						String.format(ex.getMessage(), ""), element);
				// System.out.println("ss: exception: " + GeneratorTest.class.getName());
				// ex.printStackTrace();
			}

		}
		return true;
	}
}
