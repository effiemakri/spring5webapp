package guru.springframework.controllers;

import com.sun.org.apache.regexp.internal.RE;
import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@Slf4j
public class IndexControllerTest2 {

    private Fixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new Fixture();
    }

    @Test
    public void testIndexPageFunctionsOnController() throws Exception {

        int setInControllerSize = 2;
        int getRecipesCallSize = 1;
        int calledTimes = 1;

        fixture.givenRecipeSourceIsSet();
        fixture.whenRecipeServiceGetRecipeIsCalled();
        fixture.thenReturnedStringIs("index");
        fixture.thenRecipeServiceGetRecipeIsCalledTimes(getRecipesCallSize);
        fixture.thenInControllerSizeMustBe(setInControllerSize);
        fixture.testThatModelSetAttributeSetCalledTimes(calledTimes);
    }

    /*
        Private Fixture class that helps with Give/When/Then Fixture testing methodology
     */
    private class Fixture {

        private Set sourceSet;

        @Mock
        private RecipeService recipeService;

        @Mock
        Model model;

        private IndexController controller;

        public Fixture() throws Exception {
            MockitoAnnotations.initMocks(this);
            controller = new IndexController(recipeService);
        }

        /*
            Set up test data to be used
         */
        private void givenRecipeSourceIsSet() {
            sourceSet = new HashSet();

            Recipe recipe1 = new Recipe();
            recipe1.setId(1L);
            sourceSet.add(recipe1);

            Recipe recipe2 = new Recipe();
            recipe2.setId(2L);
            sourceSet.add(recipe2);
        }

        /*
            When the getRecipes method is called, the return the object that was created for testing
         */
        private void whenRecipeServiceGetRecipeIsCalled() {
            log.info("Checking when getRecipe is called");
            when(recipeService.getRecipies()).thenReturn(sourceSet);
        }

        /*
            The returned String value myst match the input value (resultString)
         */
        private void thenReturnedStringIs(String resultString) {
            log.info("Checking return index page value");
            assertEquals(resultString, controller.getIndexPage(model));
        }

        /*
            Test that the RecipeService.getRecipe() method is called "calledTimes" number of times (should be once)
         */
        private void thenRecipeServiceGetRecipeIsCalledTimes(int calledTimes) {
            log.info("Checking how many times getRecipe is called");
            Mockito.verify(recipeService, times(calledTimes)).getRecipies();
        }

        /*
            model.setAttribute should be called once. Adding a set with key "recipes"
         */
        private void thenInControllerSizeMustBe(int setSize) {
            log.info("Checking how many recipe items are in the set");
            ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
            Mockito.verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

            Set setIncontroller = argumentCaptor.getValue();
            assertEquals(setSize, setIncontroller.size());
        }

        /*
            Verify that thte model.addAttribute is called "calledTimes" number of times
         */
        private void testThatModelSetAttributeSetCalledTimes(int calledTimes) {
            log.info("Checking how many times addAttribute is called");
            ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
            Mockito.verify(model, times(calledTimes)).addAttribute(eq("recipes"), argumentCaptor.capture());
        }
    }

}