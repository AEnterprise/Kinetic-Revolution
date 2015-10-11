package kineticrevolution.util;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.InsnList;
import jdk.internal.org.objectweb.asm.tree.MethodInsnNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;
import jdk.internal.org.objectweb.asm.tree.VarInsnNode;
import net.minecraft.launchwrapper.IClassTransformer;

import java.util.Iterator;

/**
 * Created by AEnterprise
 */
public class ASMInjector implements IClassTransformer {
	@Override
	public byte[] transform(String name, String newname, byte[] bytes) {
		if (name.equals("xj")) {
			return handleTransformation(bytes, true);
		} else if (name.equals("net.minecraft.entity.item.EntityFallingBlock")) {
			return handleTransformation(bytes, false);
		}
		return bytes;
	}

	private byte[] handleTransformation(byte[] bytes, boolean obf) {
		String targetMethod = obf ? "h" : "onUpdate";
		String targetNodeName = obf ? "B" : "setDead";
		String invoke = obf ? "(Lxj;)V" : "(Lnet/minecraft/entity/item/EntityFallingBlock;)V";
		System.out.println("***************** Performing transformation on EntityFallingBlock, obf:" + obf + " *****************");
		ClassNode node = new ClassNode();
		ClassReader reader = new ClassReader(bytes);
		reader.accept(node, ClassReader.SKIP_FRAMES);
		System.out.println("Locating target method: " + targetMethod);
		for (MethodNode method : node.methods) {
			if (method.name.equals(targetMethod)) {
				System.out.println("Target method aquired, applying patch");
				Iterator<AbstractInsnNode> iterator = method.instructions.iterator();
				while (iterator.hasNext()) {
					AbstractInsnNode targetNode = iterator.next();
					if (targetNode instanceof MethodInsnNode) {
						if (((MethodInsnNode) targetNode).name.equals(targetNodeName)) {
							InsnList injection = new InsnList();
							injection.add(new VarInsnNode(Opcodes.ALOAD, 0));
							injection.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "kineticrevolution/util/FallingBlockUtils", "onArrival", invoke, false));
							method.instructions.insertBefore(targetNode, injection);
						}
					}
				}
				System.out.println("***************** Transformation on EntityFallingBlock complete *****************");
			}
		}
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		node.accept(writer);
		return writer.toByteArray();
	}
}
