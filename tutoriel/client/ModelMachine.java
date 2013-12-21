package tutoriel.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMachine extends ModelBase
{
	ModelRenderer bottom;
	ModelRenderer propeller;
	ModelRenderer propellerPropRight;
	ModelRenderer propellerPropLeft;
	ModelRenderer lever;
	ModelRenderer leverProp1;
	ModelRenderer leverProp2;
	ModelRenderer block;
	ModelRenderer axe;

	public ModelMachine()
	{
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(0F, 0F, 0F, 16, 1, 16);
		bottom.setRotationPoint(-8F, 23F, -8F);
		bottom.setTextureSize(64, 32);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		propeller = new ModelRenderer(this, 42, 19);
		propeller.addBox(0F, -0.5F, -5F, 1, 1, 10);
		propeller.setRotationPoint(4F, 17.5F, 0F);
		propeller.setTextureSize(64, 32);
		propeller.mirror = true;
		setRotation(propeller, 0F, 0F, 0F);
		propellerPropRight = new ModelRenderer(this, 16, 26);
		propellerPropRight.addBox(0F, 0F, 0F, 1, 6, 1);
		propellerPropRight.setRotationPoint(3F, 17F, -1.6F);
		propellerPropRight.setTextureSize(64, 32);
		propellerPropRight.mirror = true;
		setRotation(propellerPropRight, 0F, 0F, 0F);
		propellerPropLeft = new ModelRenderer(this, 16, 26);
		propellerPropLeft.addBox(0F, 0F, 0F, 1, 6, 1);
		propellerPropLeft.setRotationPoint(3F, 17F, 0.6F);
		propellerPropLeft.setTextureSize(64, 32);
		propellerPropLeft.mirror = true;
		setRotation(propellerPropLeft, 0F, 0F, 0F);
		lever = new ModelRenderer(this, 11, 25);
		lever.addBox(-0.5F, -7F, -0.5F, 1, 7, 1);
		lever.setRotationPoint(-5.5F, 23F, 0F);
		lever.setTextureSize(64, 32);
		lever.mirror = true;
		setRotation(lever, 0F, 0F, 0F);
		leverProp1 = new ModelRenderer(this, 0, 28);
		leverProp1.addBox(0F, 0F, 0F, 1, 1, 4);
		leverProp1.setRotationPoint(-5F, 22F, -2F);
		leverProp1.setTextureSize(64, 32);
		leverProp1.mirror = true;
		setRotation(leverProp1, 0F, 0F, 0F);
		leverProp2 = new ModelRenderer(this, 0, 28);
		leverProp2.addBox(0F, 0F, 0F, 1, 1, 4);
		leverProp2.setRotationPoint(-7F, 22F, -2F);
		leverProp2.setTextureSize(64, 32);
		leverProp2.mirror = true;
		setRotation(leverProp2, 0F, 0F, 0F);
		block = new ModelRenderer(this, 0, 44);
		block.addBox(0F, 0F, 0F, 5, 8, 12);
		block.setRotationPoint(-3F, 15F, -6F);
		block.setTextureSize(64, 32);
		block.mirror = true;
		setRotation(block, 0F, 0F, 0F);
		axe = new ModelRenderer(this, 0, 38);
		axe.addBox(-8F, -0.5F, -0.5F, 9, 1, 1);
		axe.setRotationPoint(4F, 17.5F, 0F);
		axe.setTextureSize(64, 32);
		axe.mirror = true;
		setRotation(axe, 0F, 0F, 0F);
	}

	public void render(float f)
	{
		bottom.render(f);
		propeller.render(f);
		propellerPropRight.render(f);
		propellerPropLeft.render(f);
		lever.render(f);
		leverProp1.render(f);
		leverProp2.render(f);
		block.render(f);
		axe.render(f);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}