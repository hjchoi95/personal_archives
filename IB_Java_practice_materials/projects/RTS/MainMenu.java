/*
Hong Joon Choi
Version 0.1
14.04.2012

Objective :
add save
add battlefield
add battle
add accessories
add options, etc..
*/
/*
AI script writing strategies http://www.gpgstudy.com/gpg2/gpg2s3-1draft.html

���� #3: �����ڸ� ���� ������ ����ȭ
���� #7: ������ �Ϻκи� �ذ��Ѵ�.
���� #8: ���� ���� �̸� ó���Ѵ�

���� #10: �������� ������ ���� �󰢵� ���� ���
�̷��� ������ ������ ���̴� ���� ���º�����(influence map)�̴�. ���º������� �������� ��Ȳ �м��� ���̴� ������, �׷��� ��Ȳ �м��� AI�� �ǻ���� �ӵ��� ����
�� �����. ������ ����ʿ� ����, ���ֵ��� ���� ����(�̵�, ����, �Ҹ� ��)�� ���º������� ������ �ݿ��ȴ�. �̹� �����Ͱ� ���������� ���ŵǾ� �Ա� ������, �������� 
���� AI�� ���� ���踦 �м��ؾ� �� ���� �Ǿ Ư���� �� ���� ����. �׳� �����͸� �о �����ϸ� �Ǵ� ���̴�. �̷� ����̸� �ӵ��� ū ������ ��ġ�� �ʰ��� ����
�������� ������ ��ȸ�� �� �ִ�. �� å�� ���ϵ� Paul Tozour�� "���º�����"���� ���º������� �ڼ��� ����� Ȱ�� ����� ���� �� ���̴�.




&#8226; A* �˰�����
1. ���� ������ ��� P�� �д�
2. ��� P�� f, g, h ���� �����Ѵ�.
3. P�� open queue�� �߰��Ѵ�. �� �������� open queue���� P �ۿ� ����.
4. open queue�� ���� �� �ּ��� ��� (�ּ��� ���� f ���� ���� ���� ����̴�. )�� B�� �д�.
a. B�� ��ǥ ����̸� ��θ� ã�� ���̹Ƿ� �˰������� ������.
b. open queue �� ����ٸ� ��θ� ã�� �� ���� ���̹Ƿ� �˰������� ������.
5. B�� ����� ��ȿ�� ��带 C�� �д�.
a. C �� f, g, h ������ �����Ѵ�.
b. C�� open queue �� close queue�� ����ִ��� �����Ѵ�.
i. ���� ����ִٸ�, �� ��ΰ� ȿ����( f ���� �� ������ )���� �����Ѵ�.
1.���� �׷��ٸ� ��θ� �����Ѵ� 
ii. ������� �ʴٸ� C�� ���� ��Ͽ� �߰��Ѵ�.
c. �ܰ� 5�� B�� ����� ��� ��ȿ�� �ڽ� ���鿡 ���� �ݺ��Ѵ�.
6. 4���� �ٽ� �ݺ��Ѵ�.
�� �˰������� �⺻���� ���� �Ͽ����� �������� (sx,sy) ��ǥ���� (dx,dy) ��� �Ͽ��� ��, 
H= |dx-sx|+|dy-sy|
G= �� �ܰ踶�� 1�� �����ϴ� ���� �� (���� �� �ʱⰪ 0)
F= H+G
�� �����Ͽ���. �׷��� �츮�� ����ϴ� ��� ��ǥ(2���� ��ǥ)������ �밢�� �̵��� ������� �ʰ� �����¿� �̵����� ����ϱ� ������ �ణ�� ������ �߻��Ѵ�. �װ��� �׻� ���� f���� ������ ��尡 �� ���� �� 2���� ��Ÿ�� �ٴ� ���̴�. �׸��� ���� ������ �ּҰ��� ���� ��尡 ����Ͽ� ���ܳ� �ٴ� ���̴�. ���� ��� ��ǥ�� ��ǥ�� (4,4) �̰� �� ��ġ�� (1,1) ������ ������ ���� (1,0), (0,1), (2,1), (1,2) �̴�. �� ����� f, h, g ���� ���� ���� �����Ͽ� ���캸��,
(1, 0) h = 7, g = 1, f = 8
(0, 1) h = 7, g = 1, f = 8



=================��ã��===============
���� �帣�� ���ӿ��� ���� ���� ���� �� �ִ� ������ Ÿ�� Ȥ�� 3D����� ���� ȯ��(world)���� ���������� �̵��� �� �ִ� ��(path)�� ã�� ��
��ã�� �ý����� ĳ���� �ൿ�� Ư�� ������� �����ϴ� ����
���� �� ĳ���Ͱ� ��ֹ��� ���̿� ��� �������� ���ϴ� ��Ȳ�� ���������� �� �� ��
���� �÷��̾ ���⿡�� �ʹ� ��Ȯ�� ���� ĳ���Ͱ� �������� �ָ� ���ư��� �ϵ� �Ͼ���� �� �� ��
��ã��� ���� ���ӵ鿡�� AI�� �������� ����̸� Ư�� �����ùķ��̼�(Blizzard�� ��Ÿũ����Ʈ ���Ӱ� ����..)�� ���� �帣���� �������� ���콺�� �˷��ָ� ���� ���� ���� ã�� �ش� ��ҷ� �̵��ؾ��ϴ� ������, ���� ��� ���ӿ��� �ʿ�
�̿� ���� �ع����� A*�˰������� �ַ� ����ϴµ�, �̴� ��� ã�� �������� ���� ���� �ð��� ������������ ���� ��θ� ã����
���������� ���� ���߿� ���� ���� �� �߻��� �� �ִ� ���� ȯ��(world)�� ����( ���� ���, ������ ��ο� �÷��̾ ���๰�� �Ǽ��Ͽ� ��θ� �����ϴ� ���)�Ǵ� ���� A* �˰��������δ� ��� ��� ã�� ������ �ذ� �� �� ������ �߰����� ����� �䱸
�׷��� ��κ��� ��� A* �˰������ �ٸ� �ع��� �����Ͽ� �ذ� �����ϸ� ���� ���� ���� AI �о߿��� ���� ���� ������ ����� �о�
������ �ٴ� ���� ���������� ������ ��ã�� ��� ��
�� �� ���� ������ A* �˰������� �̿��Ͽ� �̿� ������ ��带 ã��
�� ���� �̵��� ���� �������������� �������� �� �� �ִ� �ִ� �������� ��带 �˻��ؼ� �� ������ �������� �̵�
�� ���� �α� ����(region)���� ã�� ���� �� �������� ���� ���µ��� ����
������ Dijkstra �˰�����(�׷��� �̷�)�� �̿��� �ִܰŸ� ��ã���� ��
Dijkstra �˰������� ������ ���п� ������ ����ġ�� �����ϰ�, ���� ��ġ�� �ٸ� ��� ���鿡�� �� �� �ִ� �ִ� ��θ� ����
�� �˰����� ��Ÿ���� �׷����� �� ���� �θ� ���� ���� ��밪�� �����ͷ� ����
���ʿ� ��� ������ ���� ���� ������ �ùٸ��� ���� ���� ������ ���� ����� ���Ѵ�(infinity)�� �����Ǹ� ���� ����� ���� ����� 0���� ����
������ ��θ� ã�� ���� ���
�켱 �ݺ������� ��带 ��� ��ķκ����� ���� ���� �ּ� ������� ��ü
�׸��� �װ͵��� �� ���п� ���ؼ� ��� ���п� ���� ��ǥ ��带 ���� ������ ���� ����� ���� ����� ��뿡 ������ ����� ���� �ͺ��� ũ�� ��ǥ ��带 ���� �� ���� ��θ� ã�Ƴ� ��
�׸��Ͽ� ��ǥ ��忡 ���� ����� �����ϰ� �θ� ����� ������ �����ϸ�, �׵��� ���� ���� ���ϰ� ��
������ Dijkstra �˰������� ���� �ǻ��ڵ�



================���� �ൿ===========
���ӿ��� ������ �ൿ�� ������ ���� �� ���� ����
�׷��� �ռ� ����� ��ã�� ���� ���ӿ� �����ϴ� �÷��� ������ ���ֵ��� ������ �������� ���� �ݵ�� �䱸�Ǵ� �κ� �� �ϳ�
���� �̵� ������ ��ΰ� �������� �ұ��ϰ� ���̸Ӱ� ���ϴ� ��ġ�� ������ ����� �̵����� ���Ѵٸ� ���̸Ӵ� �� �̻� ������ �����ϰ� ���� ���� ��
����, ���̸Ӵ� ���ӿ��� ������� �ý��� �÷��̸� �ϱ⸦ ��ġ ���� ��
���� ������ ������� �������� �ൿ�� �ϵ��� ������ �����Ͽ� ���ӿ��� ū ��ſ��� �������� �ؾ���
���� ���, ���̽� ������ ��� ���� �Բ��޸��� ������ �ʹ� �����ٰų� �ʹ� �ռ� �޸��ٰų� �ؼ��� �÷��̾��� ��̸� ������ �� ���� ��
��ȹ
������ ���������� ������ ����ȭ�� ĳ���Ͱ� �����Ҽ��� ĳ���ʹ� ������ �ൿ�� ������ ��� ������ �ִ����� �����Ͽ� ��ü���� ��ȹ�� ����� ����
���� ��� �����ùķ��̼� ���ӿ��� ������� ���� �� �ִ� �ൿ(������� ���� ����, �� ������ ���� ���� ��)�̳� Ư¡�� �����Ͽ� ��ü���� ���� ����(����� �� ������ ��� ���� ���� �ľ�)�� ��ȹ�Ͽ��� ��

===============AI ����ȭ ����============
������ AI�� ����� ���� �ɷ��� �䱸�ϸ� ������ �ڵ�ȭ ������Ʈ���� ���������� ���� ���迡�� Ȱ���ϵ��� �Ϸ��� ���� �� ���� ���� �ɷ��� �ʿ�
���� ������ �⺻������ �ǽð��� �������� ����� ũ�� �䱸�Ǵ� �������α׷������� AI�� �оߴ� Ư�� �䱸 ��
��� AI ������Ʈ���� ���ÿ� �ڽ��� ������ ������ �����̴� ��ó�� ���̰� �ϴ� ���� ������ ����� �����̸� �̸� ���� AI ����ȭ�� ���� �̰�ô �о�
�׷��� �Ųٷ� �����ϸ� �̷��� ������� ���ļ��� AI �о��� ���ο� �������� ����� �����̶� �� �� ����
�̷��� ���ļ��� AI ������Ʈ���� �ٸ� ���� Ư����� ���������� AI���� �䱸�Ǵ� ����ȭ ����� �� �� ����

���� #1 : ���� ��� �̺�Ʈ �ֵ��� �ൿ�� ���
�̷�������, �ڵ�ȭ ������Ʈ�� �ڽ��� ���踦 ���Ӿ��� �����ϰ� �׿� �°� �ൿ�ؾ� ��
������ ����Ǵ� �� �����Ӹ��� ������Ʈ���� �ڽ��� �����ؾ� �� ��� �Ǵ� ��ü���� ����
�̷��� ���� ����� ��û�� ���� �ߺ��� ����� �߻��Ѵٴ� ��
�� �̷��� �������� ����(polling)�� �ڿ� ���� ���ϴٰ� ���� �� ����
�̿� ���� ����� ���̺�Ʈ �ֵ���(event-driven) ������� ����ϴ� ��
���� ���������� ����. ���۰� ������ �غ��ϰ� ������, �̸� ������� ���ߵ��� ���Ѻ� ���, ���� ���� Ȧ�ſ� ���� �� ���� ���� ������ �޽����� �����ٰ� ����
�̴� ��� ������ ���� �����̴� ���� ���� �ֽ��ϵ��� �ϴ� �� ���� �ξ� �� ȿ����
���� �̷��� �ϳ��� ���� �ƴ� ���� ���� ���� ��ü�� ���ÿ� �߻��� ���(�������� ������ ������ ������ �� ���� ����..)�� ������ ū ������ �� ��
���� #2 : �ߺ��� ����� ����
���ӿ��� ������ AI ������Ʈ���� �����ϸ�, �׵��� ���÷� ���� ���� ����� �����Ѵ�. �̷��� ��Ư�� ȯ�濡���� �ӵ� �� ȿ������ AI ó���� ���ؼ��� ������ �ϵ���� ��� �������� �ʴ� �̻� �ϵ��ڵ����� �غ��ϰų� ������ ó�� ����� ����ؾ� ��
������ �� ������ ������ AI ������Ʈ���� �ڽ��� ���, ó�� ����� �����ϵ��� ����� ���ʿ��� �ߺ� ����� ���̴µ� �� ��ǥ
AI ������Ʈ�� ������ ������ �ִ� ������ ������ �� �ִٸ� ��� �ð��� ����� ���� �� ���� ��
������ ���� ��ã��
���� �ùķ��̼� ����(�� : BLIZZARD�� ��Ÿũ����Ʈ)������ ������ ���ֵ��� �����ϸ� �÷��̾�� �̸� ���콺��Ű���带 �̿��� �����ϰ� �Ǹ� �̶� �Ѳ����� ���� ���� ������ ���� �ٸ� �������� �̵��ϵ��� ����
������ ���ֵ��� �ش� ���ɿ� ���� ��ã�� ������ ���� �̵��ϰ� �Ǹ� �̶� ��� ������ ��ã�⸦ �����ϴ� �� ���� �� ������ ��ã�⸦ �����ϰ� �������� �� ������ �ڸ� ���󰡴� ���� �ξ� �� ȿ������ ��
�̷� ��� ��ã�� ������ Ƚ���� �ް��Ͽ� ������ �ð��� ����� ���� �� ����
���� #3 : �����ڸ� ���� ������ ����ȭ
AI�� ���� ������ �����ϰ� ����� ���� ����� �ϳ��� ���ӳ��� AI ������Ʈ���� �ϳ��� ����(Group)���� �����ϴ� ��
�ϳ��� ���ܿ��� �� ������ �̲��� ������ ���� ������ ������Ʈ�� ���� �� ������ ���� �帧���� �Ϻ�(Ư�� �� ���ܺ� �ൿ�� �䱸�Ǵ� ���..) �������� �����ڿ��� �����ϴ� ��
������ ���õ� ������ �����ϴ� ���� ������ ������ �ð� �ִ� ������Ʈ�� AI�� �Ǵ��ϰ� ��
���� ���, �౸ ���ӿ��� ��3-5-2�� �ý������� ������ ����ٰ� ���� ��, ���ݼ�(3)�� �̵��ʵ�(5), �׸��� �����(2) ������ ������Ʈ�� �����ϸ�, �̵��� ȿ������ ���ݰ� ���� ��ġ�� ���� ������ �����ڸ� �� �� ����
�̿� ���� ����� ���ݼ��� ���ݿ��� �����ϵ��� �� �� ������ �̸� ���� ������� �̵��ʵ���� ���� ȥ���� ���� �� �ְ� ��ĩ������ �Ѿ� �����ٴϴ� ������ �౸���� Ż���� �� �ִ� �౸ ������ ���� �� ����
���� #4 : ó�� �δ��� ���� �����ӵ�� �л�
�� ������ ��ã��� ���� CPU�� �ڿ��� ���� �Һ��ϴ� AI �κп� ����ϸ� ����
��ã���� ����� �Ǵ� �˻� ������ �� �����Ӹ��� ũ�� ���ϴ� ���� �ƴϹǷ� ����� ���� ���������� ������ ���� ����
�̷��� ���ٹ���� ������ �� ó�� �δ��� ���̴µ� ȿ����
���� #5 : ���� ���� �̸� ó��
������ ������ ������ ž��� ���ӵ��� ��� ����
�̷��� ������ Ư¡�� ���� ���� ����� �䱸�Ǵ� ������ �ַ� �����Ѵٴ� ��
���� �ùķ��̼��� �� ��ǥ���� ��
�̷��� ������ ���� �ǽð����� ó���ϱ⿣ �ʹ� ����� ������ �����ϱ⵵ ����
�̷��� ������ �ذ��ϱ� ���� ������ ����� �̸� �����Ͽ� ���̺��� ����ְ� �����ϴ� ����� ����ϴ� ���� ����� ����Ͽ� ���� �������� �� �Ѽ����� ��ȸ������ �ǻ� ������ �Ǵܿ� �ʿ��� ������ ���� �� �ְ� ����� ���� ����
�̷��� ������, �� ��ų�ι���Ʈ�� �����ͷ� ��û�� CPU �ڿ��� ������ �� �ְ� ���شٴ� ������ �ſ� ������ ����ȭ �����̶� �� �� ����
�� �ܿ��� AI�� ����ȭ�� ���� �������� ���� ����
������ AI �ý��ۿ��� ������ �� �ִ� �������� ����ȭ�Ϸ��� �� �ٸ� �ð����� ������ �ٶ󺸴� ���� ������ �� ��



=============���ӿ��� FSM�� ����� ���==============
�츮�� �˰��ִ� ������ ���� �� ����ũ(Quake)�� NPC(Non Player Character)�� �ൿ ��� FSM�� ����Ͽ�����, �̿� ���� �帣�� ������ Half Life ���� ������ FSM(Hierachical FSM)�� ����� ������ �˷��� ����
�Ϲ������� ���� ������ ĳ������ �ൿ�̳� ���� ���µ��� FSM���� ǥ�� ����
���� ĳ���� Ȥ�� �ϳ��� ĳ���Ϳ� ���� ���� ���°� �־����� ���� �ܺ��� �ڱص ���Ͽ� ���°� �ٲ� ��� ���� ���¿� ���� �׿� ���� ���� ����� ����
�ڱذ� ���� �ܺ��� ��Ȳ�� �ٲ�� ���� ���°� �ٸ� ���·� ����
���� ���� ����ġ������ ���� ĳ���ʹ� Ư�� ���¿� ���� ��쿡�� �׻� ���� ������� �ൿ
���� ĳ���Ͱ� �� ���� ���·� �־��� �� ĳ������ ������¿� ���� �ܺο� ��ó�ϴ� ����� ����
��, �ܺ��� ��Ȳ�� ��ȭ�ϰ� �Ǹ� ������µ� FSM�� ���� �ٸ� ���·� ����
����, ĳ���ʹ� Ư���� ���¿� ���� ��� �׻� ���� ������� �ൿ
��������� FSM�� �������� ���̵��� �ſ� �����ϰ� �ܼ�������, �� Ȱ�� ������ �뵵�� �ʹ����� �پ��ϴ� �������� �ý��� ��� �ٺ��� �Ǵ� �˰������̶�� ���� �� �� ����
������ �ܼ��� switch() ���� Ȥ�� if ������ ����Ͽ� ���α׷��ֵǴ� ���� ����
�׷��� FSM�� Ư���� �ΰ����� ����� �䱸���� �ʴ� ���ӿ��� ���� ����ϸ� ���� ����/���� �������� ������ �����þ�� �Ǹ� switch() ������ ����ϴ� ��� �迭�� ����Ͽ� ���ϴ� ��ƾ�� ������ ����
FSM�� �ǻ��ڵ� ����
Switch(creature_state)
	case STATE_ATTACK :
		�÷��̾� ������ �̵�
		20%�� Ȯ���� �� �߻�
	case STATE_RETREATE :
		�÷��̾�� �ݴ� �������� �̵�

FSM�� ǥ���ؾ��� ���¼��� �þ�� �Ǹ� ��� ����
FSM�� ��Ÿ�� �� �ִ� ���� ���� ��� ���� ������������ ��ǻ� �� ���� ���� ������ ���� ������ �����ϰ� ���� ���� ���� ǥ���� �䱸�ϴ� ��� ���� ���̾�׷��� �����ϱⰡ ��ư� ���� ��ȭ�� �����ϰ� �ϴ� �ܺ� �ڱ� ó���� ���������� ��
�Դٰ� FSM�� ����� ���� Ȥ�� ���ӳ��� ĳ���ʹ� �� �ൿ�� ������ ����� �׷��� �� ��� ������ ��̰� ��������, ������ ���̵��� ���̱� ���� FSM�� ���� Ȯ�� �����ؾ��ϴ� ����� �ʷ�
������ ���� ���� ���� FSM�� ����ϰ� ��ȭ, Ȯ��ǰų� ������ FSM���� �ٽ� ����


============FuSM(Fuzzy State Machine)==============
FuSM�� FSM�� ������ �����ϱ� ���� ������� ���� �̷��� FSM�� ���� ���̸� ���� ���� �Ǵܿ� ������ ��
FuSM�� ������ �Է°� ��¿� ���� �Լ��� �����Ͽ� ���� ����� �߰������ν� ������ �Է¿��� �ٸ� ���·��� ���̸� ���� �� �ִ� ���
���� ���� ���� ���� ���׷��١��� ���ƴϴ١���� �̻����� ���� ��� ���ſ� �׷��١�, ������ �׷��١�, ���ټ� �ƴϴ١�, ���ſ� �ƴϴ١��� ���� ���µ��� ǥ��
�̰��� �ϳ��� (�ٺ��� ������������) ���� �л��Ű�� ����Ʈ���� ���� ������ ���� ���ذ� ���� ��
������ FuSM�� ���ӿ� �̿��Ѵٸ�, ���� ��� �ϳ��� NPC(���⼭�� �ڿ� �����̶� ���ٸ�) �� �÷��̾ ���� �ܼ��� �������١�, ������ �����١�, ���ſ� �����١��� ���� ���·��� ���̰� ������ ��
�̴� ���� ���� ���� ���µ��� �ݵ�� ��ü���̰� �̻����� �ʿ䰡 ������ ���ϱ⵵ ��
�Ϲ������� FuSM�� �ϳ��� ���¸� ǥ���ϱ� ���� ������ ������ ���� �Ǽ��� ǥ��
���� �Ǽ��� ������ 0.0���� 1.0������ ������ ������ �̰��� ���� �̷п��� ���ϴ� ������ ���� ǥ���ϱ� ���� ��d���� ����� �ƴ�
���� ����ϰ��� �ϴ� ������ ���� ���̶�� �������� ����� �� ����
�׷��ٸ� �� ���ӿ��� FuSM�� ����ϴ°�? �츮�� ���ӿ� ���� ������ �����Ŵ���ν� ���� �߻����� ������ ǥ���� �� ������ �̸� ���� ���ӳ��� ��ü(NPC�� ����..)��κ��� ���� ����ִ� ������ ����� ���� �����ϱ� ����� �������ν� ���� ��ü�� ��̸� ��� ��ų �� �ֱ� ����
������ ���� �÷��̾�� ����ġ�� ���� ������ NPC Ȥ�� ����(monster)���� ���� �ٸ� �ൿ ������ ���� �� �ְ� �Ǹ� �̷ν� ������ ���� �� ������������� ��
�����ε� ���� �ɸ��Ͱ� �ٸ��� ���̱⵵ ��
�پ��� ���� �帣�鿡�� FuSM�� �����ϰ� ���ǰ� ����
RPG ������ �ϳ��� ��ƺ�(DIABLO)�ο��� ĳ���ʹ� �� ���ֵ�κ��� ���� ���� ���� ���ط� ���� �ൿ�� �������ٰų� ü���� �پ��ٰų� �ϴ� ��ȭ�� ���÷� ����
�̴� ����� ���� ���°��� �䱸�ϴµ� ������ ���� ����� ���� Ȥ�� ������ �ɷ�ġ�� ���� ������ ũ��� ���� �ð����� ��ä�Ӱ� ����
���̽� ������ �ϳ��� �ϵ������ǵ�(Need for Speed)���� ��� ���� �ڽ� ��Ż�� �ϰų� ������ �μ����ų��ϸ� ���� ��ġ�� �Ը� ���� ������ �ӵ��� �ڵ鸵 � �پ��� ������ �ְ� ��
�̴� ������ ������ ���� �����ϰ� �ϰԲ� �����ϸ� ��ǰ��� ������ ������ ȿ������ ����
���������� ��ǻ�͸� ���� ���ӿ� �����ϴ� ���� �׸� ����� ���� �ƴϸ�, ������ Ư���� ����� �� �ִ� �پ��� ������ ������ �䱸�Ǵ� ������ ���� ������ ������ �� �κ����μ� �Ϻ��ϰ� ��� ����



=============����� ������(influence map) ���=============
����� ������ ����� ������ �Ǵ��� �����µ� �� �ʿ��� ���� AI �������, ���� ���ӵ鿡�� �������� ����
����� �������� �ַ� ���� ���ӿ� ���̳�, ������ �м��� �ʿ��� �ٸ� ������ ���ӵ鿡���� �����ϰ� ���� �� ����
����� �������� AI ������Ʈ�� ���迡 ���� ���ϰ� �ִ� ������ ���������� ǥ�� ��
������ ���� ȯ���� ������/������ ǥ���� ������ ���� ����(���� �� �������� ��ġ��)�� ����ؼ� ������ ������ �ʿ��� �������� ������ ������ ���̶� �� �� ����
��ǻ�� �÷��̾�� ����� �������� ���� �ڽ��� ���� ��ġ�� ���� ��ġ, �׸��� ������ ���� �� �����ϱ� ���� ������ ���� ������� ���� �ľ��� �� ����
����� �������� ����� ���� ����� ���� ������ ������ ���� ��� ���õ� ���� ������ ����
���⼭�� ���� ������ ���ؿ��� ������ �˾ƺ� ��
2���� ���������� ����� ������
����� �������� ��κ��� ���� ������ ���ؼ��� ���� ����
���� ���� Ÿ�� ����� ����(���簢��, ���簢��,������ ��) ���¸� ���� �� ������ 3D������ ���� ����
���⼭�� 2���� ������ ������ ������ ���� ���ظ� ������
���� ����� ����� ĭ��� ����
�׸��� �� ĭ���� ��� ������� 0���� �ʱ�ȭ
�׸��� �� ĭ�� ���� �� �ִ� ����� ���� ����
�̶� ������� ���� ������ ���� �ɷµ����� �� �� ������ �Ʊ��� ���, ������ ������ ���� ����
�ϳ��� Ÿ�Ϸ� �̷���� ���� ���迡�� ������ ������� ���� �Ʊ��� ������ �����ϸ�, �̵��� ����� ���� ������ ���ϰ� �����ν� �� Ÿ�Ͽ����� ���� ���� �ɷ��� ��
�ϴ� �� ������ 1�̶�� ���� �ɷ��� �����ٰ� ����.
���� �� ĭ�� ������� ���� ĭ��� ���Ľ��Ѿ� ��
������ ĭ���� ���ĵ� ������ ����(�����)�� ������ �شٰ� ����
�� ���� ����(�����)���� ����� ���� �����ϰ� ���ĵǸ�, �� ������ ������� ���� ������ ��Ÿ���� ��
�Ʊ� ���ְ� �� ������ �� ������� ������ �������� ���� �츮�� �Ʊ��� ������ �����踦 Ȯ���� �� �� ������ Ÿ�� ���� �����(���� ���� ���ǰ�)�� �̿��Ͽ� ������ ������ ���� �������� �ľ�
���� ���ӿ� ���̴� �������� ���´� ���� ����
���� ���ӿ����� ���� �� ĭ�� ����� ������ �ƴ� ������ ���迡 ���� �� �ٸ� ����( �����ɷ�, ���� ���ü�, ������ ��, �ڿ�, ���༺ ��)���� �Բ� ��� ��찡 ����
�̷��� ������ ����� �������� �ϳ��� �����ͺ��̽��� �Ǳ⵵ ��
�Ϲ�������, ���ӿ� ������ �÷��̾�鿡�� ���ڿ� �ش��ϴ� ����� �������� �־����� �ǰ� �̸� ó���ϱ� ���� �������� ���������� ó���ؾ� ��
���� ��� �÷��̾�鿡 ���� �ϳ��� ������ ����º������� �����ϰ� ��� AI�÷��̾���� �װͿ� �����ϰ� ���� ���� ����
�̰��� ġ��(cheating) ������ �� �� ����

==============�ڿ� ���� Ʈ��============
����� ��������� ���� ������ ������ �ڻ�(asset)�� ��� ���� �����̶� �� �� ������ ������ ���캼 �ڿ� ���� Ʈ���� ������ ������ ������ �����µ� ���Ǵ� ��� �� �ϳ��� �� �� ����
�ڿ� ����Ʈ���� �÷��̾��� ���� �Ͽ� �ִ� ��� �ڻ���� Ư���� ����� ������ ǥ���ϴ� �ϳ��� Ʈ�� ����
�� Ʈ���� ���� �÷��̵ǰ� �ִ� ��� ���ֵ�� �ڿ����� ����� ���ֵ��� ���뱸���� ����ȭ�� ����
�ڿ� ���� Ʈ���� �̿��� �÷��̾�� �ڽ��� ������ ���Ӿ��� ��� �÷��̾���� ������ ������ ������ �˾Ƴ��� �� ���� �� ����
���� �� Ʈ���� �پ��� ������ ������ ���� �� �ڿ� ��� ������ ���� ��� ����
���� ��� ���� ������ ���� ������ ������ �����Ѵٵ���, �׸��� ��� ����� ������ �ñ� �������� �Ǵ��ϴµ� ���
�ڿ� ���� Ʈ���� �⺻������ � �� ���ֵ��� ������ ���̸� ���� ���ֵ鿡�� � ������ �ñ� �������� �����ϴµ� ����
���� �÷��̾��� ��Ư�� ������ â���ϴ� ������ �� ���� ����
�� �������� AI �÷��̾���� �����µ� ��� ����
�������� AI �÷��̾���� ������ ������ ���� Ʈ�� �� ����� ��� �������� �����Ͽ� �ذ� �����ϸ�, ���� �� ������ ������ �����ϹǷν� ������ ������ �����ϴ� �� ���� ����


*/
class MainMenu extends DataBase
{

	// compatible in 35x100 size terminal
	static Player player[] = new Player[8];
	static DataBase database=new DataBase();

	static int input = 0;
	public static void main(String[] args) 
	{

		player[1] = new Player();

		//////////////////////********************Load Data********************//////////////////////
		System.out.println("RTSProject. (Last edit 14.04.2012) HongJoon");
		System.out.println("Enter 1 to load. To generate new profile, enter 0.");
		if (safeInput()!=1)
		{
		}
		else
		{
			try
			{
				player[1].loadPlayer("saveData1");
			}
			catch (Exception e)
			{
				System.out.println("Error has occured while loading the file : "+e);
				return;
			}
			
		}
		System.out.println("Lodaing Successful.");
		//s.next();
		//////////////////////********************Main Menu********************//////////////////////
		//Battle b = new Battle(player[1], player[1].getConstructedUnit(), 8, player[2], player[2].getConstructedUnit(), 3);
		//b.battle();
		





		MainMenuLoop : while (true)
		{ 
			clearscreen();
			System.out.println("			   ==========Main Menu=========\n\n");
			System.out.println("				[1] Player Statistics");
			System.out.println("				[2] Machine Shop");
			System.out.println("				[3] Enter Campaign\n");
			System.out.println("				[5] Save\n");
			System.out.println("				[7] Options");
			System.out.println("				[8] Manual\n");
			System.out.println("				[0] Exit Game");
			printLine(10);
			switch (safeInput())
			{
			case 1:
				information(1);
				break;
			case 2:
				machineShop(1);
				break;
			case 3:
				campaign(1);
				break;
			case 5:
				save(1);
				break;
			case 7:
				
				break;
			case 8:
			
				break;

			case 0:

				break MainMenuLoop;
			default :
				break;
			}
		}
		
		
		
		

		
		
	}

//========================================PlayerInformation==========================================
	public static void information(int playerNo)
	{
		infoLoop : while (true)
		{
			clearscreen();
			player[playerNo].printPlayerStats();
			printLine(5);
			
			System.out.println(pad("[2] Inventory",20)+pad("[3] Hanger",20)+pad("[4] Lab",20)+pad("[5] Base Skill",20)+pad("[0] Escape",20));
			input=safeInput();
			if (input==2)
			{
				clearscreen();
				System.out.println("\n		[Leg Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getLegOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(legParts[i].getName(),15)+" x "+player[playerNo].getLegOwn()[i]);}
						catch (Exception e){break;}
					}
				System.out.println("\n		[Body Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getBodyOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(bodyParts[i].getName(),15)+" x "+player[playerNo].getBodyOwn()[i]);}
						catch (Exception e){break;}
					}
				System.out.println("\n		[Weapon Parts]");
				for (int i=1;i<50 ;i++ )
					if (player[playerNo].getWeaponOwn()[i]>=1)
					{
						try{System.out.println(pad(i+".",2)+pad(weaponParts[i].getName(),15)+" x "+player[playerNo].getWeaponOwn()[i]);}
						catch (Exception e){break;}
					}
				s.next();
				/*
				System.out.println("\n\nEnter 5 to slelect item to discard. Enter other key to continue..");
				if (safeInput()==5)
				{
					System.out.println("Select Inventory section     [1]leg [2]body [3]weapon");
					System.out.println("(You can enter any other key to escape)");
					input=safeInput();
					if (1>input || input>3)
						break;
					System.out.println("Enter name of item to discard");
					if (input==1)
						searchFor(s.next(),legParts,50);
					
				}*/
			}
			else if (input==3)
			{
				clearscreen();
				System.out.println("					[Hanger]\n");
				player[playerNo].printUnitInfo();
				s.next();
			}
			else if (input==4)
			{
				int legSelect;
				int bodySelect;
				int weaponSelect;
				int hangerSelect;
				String name;
				char indicative;

				clearscreen();
				System.out.println("					[Lab]");
				System.out.println("You can construct your own units with parts that you have.");
				do
				{
					System.out.println("Decide which slot in your hanger you are going to put unit in.");
					System.out.println("(Keep in mind that you can escape at any stage by entering 0.)");
					hangerSelect=safeInput();
					if (hangerSelect==0)
						continue infoLoop;
				}
				while (hangerSelect>9 || hangerSelect<1);
				
				do
				{
					System.out.println("\nSelect LEG parts ( ex) Enter 1 if you want Walker)");
					legSelect = safeInput();
					if (legSelect==0)
						continue infoLoop;
					if (player[playerNo].getLegOwn()[legSelect]<1)
						System.out.println("Not enough inventory!");
				}
				while (player[playerNo].getLegOwn()[legSelect]<1);
				System.out.println("\n"+legParts[legSelect]);
				do
				{
					System.out.println("\nSelect BODY parts ( ex) Enter 1 if you want Infantry)");
					bodySelect = safeInput();
					if (bodySelect==0)
						continue infoLoop;
					if (player[playerNo].getBodyOwn()[bodySelect]<1)
						System.out.println("Not enough inventory!");
				}
				while (player[playerNo].getBodyOwn()[bodySelect]<1);
				System.out.println("\n"+bodyParts[bodySelect]);
				do
				{
					System.out.println("\nSelect WEAPON parts ( ex) Enter 1 if you want ArmourGun)");
					weaponSelect = safeInput();
					if (weaponSelect==0)
						continue infoLoop;
					if (player[playerNo].getWeaponOwn()[weaponSelect]<1)
						System.out.println("Not enough inventory!\n");
				}
				while (player[playerNo].getWeaponOwn()[weaponSelect]<1);
				System.out.println("\n"+weaponParts[weaponSelect]);
				System.out.println("\nEnter any key to continuue");s.next();
				clearscreen();
				do
				{
					System.out.println("Enter name of your unit (less than 18 words)");
					name=s.next();
				}
				while (name.length()>18);
				while (true)
				{
					System.out.println("Enter indictive character of your unit in battle (1 word)");
					try
					{
						indicative=firstLetterOf(s.next());
						break;
					}
					catch (Exception e){}
				}
				
				System.out.println("\nHanger number : "+hangerSelect+"\n"+legParts[legSelect]+"\n"+bodyParts[bodySelect]+"\n"+weaponParts[weaponSelect]);
				System.out.println("Enter 1 to confirm your choice. Any other key will delete your decision.");
				if (safeInput()==1)
				{
					player[playerNo].editUnit(name, indicative, hangerSelect, legSelect, bodySelect, weaponSelect);
					player[playerNo].useLeg(legSelect);
					player[playerNo].useBody(bodySelect);
					player[playerNo].useWeapon(weaponSelect);
				}
				else
					continue infoLoop;
				System.out.println("Unit construction successful!! Enter anykey to return..");
				s.next();

			}
			else if (input==4)
			{
				clearscreen();
			}
			else
				break;
		}
		
	}
//=====================================================Shop==================================================
	public static void machineShop(int playerNo)
	{
		shopLoop: while (true)
		{
			clearscreen();
			System.out.println("Your Cash: $"+player[playerNo].getMoney());
			printLine(3);
			System.out.println("			{Machine Shop}\n\n");
			System.out.println("			[1] Leg Parts\n");
			System.out.println("			[2] Body Parts\n");
			System.out.println("			[3] Weapon Parts\n");
			System.out.println("			[4] unit Upgrades\n");
			System.out.println("			[0] Escape");
			System.out.println("");
			System.out.println("");
			System.out.println("\n\n");
			switch (safeInput())
			{
			case 1:// legParts
				clearscreen();
				System.out.println("				[Leg Parts Shop]\n");
				for (int i=1;i<=10 ;i++ )
					System.out.println(pad("[1"+(i<10?"0":"")+i+"] $"+legParts[i].getCost(),10)+legParts[i]+"\n");
				input = safeInput()-100;

				if (0<input && input<=10)
				{
					if (player[playerNo].getMoney()<legParts[input].getCost())
						break;
					player[playerNo].buy(1, input, legParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;
			case 2:// bodyParts
				clearscreen();
				System.out.println("				[Body Parts Shop]\n");
				for (int i=1;i<=10 ;i++ )
					System.out.println(pad("[2"+(i<10?"0":"")+i+"] $"+bodyParts[i].getCost(),10)+bodyParts[i]+"\n");
				input = safeInput()-200;

				if (0<input && input<=10)
				{
					if (player[playerNo].getMoney()<bodyParts[input].getCost())
						break;
					player[playerNo].buy(2, input, bodyParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;

			case 3:// weaponParts
				clearscreen();
				System.out.println("				[Weapon Parts Shop]\n");
				for (int i=1;i<=10 ;i++ )
					System.out.println(pad("[3"+(i<10?"0":"")+i+"] $"+weaponParts[i].getCost(),10)+weaponParts[i]+"\n");
				input = safeInput()-300;

				if (0<input && input<=10)
				{
					if (player[playerNo].getMoney()<weaponParts[input].getCost())
						break;
					player[playerNo].buy(3, input, weaponParts[input].getCost());
					clearscreen();
					System.out.println("Purchase successful! Enter any key to return...");
					s.next();
				}
				else
					break;
				break;

			case 4:// Upgrade
				clearscreen();
				int unitSelect;
				System.out.println("				[Unit upgrade Shop]\n");
				do
				{
					System.out.println("Select category of unit in your hanger to upgrade.");
					unitSelect=safeInput();
				}
				while ((unitSelect<1 || unitSelect>9) || player[playerNo].getConstructedUnit(unitSelect).getWepType().equals("-"));
				System.out.println("\n"+player[playerNo].getConstructedUnit(unitSelect).printInformation()+"\n\n");
				if (player[playerNo].getConstructedUnit(unitSelect).getUpgradeCount()>=5)
				{
					System.out.println("Unit is already fully upgraded!! Enter any key to return.");
					s.next();
					break;
				}

				System.out.println(pad("[401] $30000 ", 15)+"Increase max Hp by 5%");
				System.out.println(pad("[402] $30000 ", 15)+"Increase weapon damage by 5%");
				System.out.println(pad("[403] $30000 ", 15)+"Increase weapon accuracy by 5%");
				System.out.println(pad("[404] $30000 ", 15)+"Increase leg stability by 2");
				System.out.println(pad("[405] $30000 ", 15)+"Increase max Load by 5%");
				System.out.println(pad("[406] $30000 ", 15)+"Upgrade watt efficiency of unit by 5%");
				do
				{
					input = safeInput()-400;
					if (input==-400)
						break shopLoop;
				}
				while (input<0 || input>6);
				
				if (player[playerNo].getMoney()>30000)
				{
					player[playerNo].getConstructedUnit(unitSelect).upgrade(input*100+5);
					player[playerNo].pay(30000);
					System.out.println("Succefully upgraded! Enter any key to continue..");
				}
				else
					System.out.println("Not enough money! Enter any key to return...");
				s.next();
				break;

			case 0 :
				break shopLoop;

			default :
				break;
			
			}
		}
	}

//=====================================================Shop==================================================
	public static void campaign(int playerNo)
	{
		while (true)
		{
			clearscreen();
			System.out.println("			---------Single Player---------");
			System.out.println("");
			System.out.println("");
			System.out.println("			[1] Campaign  (Stage "+player[playerNo].getCampaignStage()+")");
			System.out.println("");
			System.out.println("			[2] Custom match\n\n\n");
			System.out.println("			[3] Connect to network\n\n\n\n");
			System.out.println("			[0] Escape\n\n");
			input=safeInput();
			if (input==1)
				mapLibrary(player[playerNo].getCampaignStage());
			else if (input==2)
				mapLibrary(1+100);
			else if (input==0)
				break;
			else
				continue;
		}
		







	}


//=====================================================Save==================================================
	public static void save(int playerNo)
	{
		clearscreen();
		try
		{
			player[playerNo].save("saveData1");
			System.out.println("save successful. Enter any key to return...");
		}
		catch (Exception e)
		{
			System.out.println("Error occured while saving the file");
			System.out.println("Caused by : "+e);
			System.out.println("Enter any key to return...");
		}
		
		s.next();
	}








	public static void mapLibrary(int mapNumber)
	{
		Map map = new Map();
		int[] setting = new int[200];//first two letters : location, third= AreaProperty, last three : occupiedBy, build n Unit
		int[] triggerConditionGroup = new int[20];
		String[] triggerActionGroup = new String[20];
		boolean[] AIcontrol = new boolean[10];
		player[2] = new Player();
		player[2] = new Player();
		player[3] = new Player();
		player[4] = new Player();

		switch (mapNumber)
		{
		case 1:
			player[2].editUnit("armourGun",'a', 1, 1,1,1);
			player[2].editUnit("machine",'m', 2, 1,1,2);
			//first two letters : location, third= AreaProperty, last three : occupiedBy, build n Unit
			setting = new int[]{420000, 520000,620000,630000,531100,430000,443000, 460000,550000, 540000, 450000, 560000, 640000,660000,470000, 770000, 761241, 750000, 650231, 670232, 570000};
			
			triggerConditionGroup = new int[]{1001, 1001,4103, 2144, 2144, 2144, 3200};
			triggerActionGroup[0]="message//\t\t\t\t\t[Boot camp]\n\n\n\t\tDistrict 13 of capitol dominion\n\t\tTarsonia sector\n\t\tWidmoor camp";
			triggerActionGroup[1]="message//\t\tWelcome to tutorial of Capital Insurrection. You are in widmoor outpost.\n\t\tFirst thing you have to do here is to build units and raise your army.\n\t\tUse [wasd] to move your cursor, select your main base (indicated by ��) \n\t\tand enter 1 to build unit.\n\n\t\tObjective 1 : Build three armour guns.";
			triggerActionGroup[2]="message//\t\tWell done! Area with small army is indicated by *, \n\t\tand number at each point indicates occupication status. \n\t\tSymbol changes as you increase size of army in area. \n\t\tNow you can advance your army to nearest strategical point.\n\t\tAdvance your army to mountain shaped point. That is mining zone.\n\t\tOccupying that area will boost your income \n\n\t\tObjective 2 : Advance your army to mining zone,   .";
			triggerActionGroup[3]="message//\tSuccess! You occupied mining zone! This will boost your income! \n\tKeep in mind that income will be only maintained until opponent force invade mining zone.\n\tStrategical Movement is key factor in this game.\n\tYou have to be able to split your army efficiently.";
			triggerActionGroup[4]="message//\t\tDammit! Dominion found our outpost!\n\t\tThey are trying to attack us! You must hold their push!\n\t\tWith your fund, build army to fight against their armies. \n\t\tYou only have armorgun to build for now.\n\t\tHowever, you will be able to build diverse types of army afterwards.\n\n\t\tObjective 3 : Hold dominion's attack.";
			triggerActionGroup[5]="AIscript:AllInAttack//2";//"AIscript:AllInAttack//2";
			triggerActionGroup[6]="defeat//2";
			AIcontrol = new boolean[]{false, false, true};
			map.editCampaignMap(setting, player);
			map.campaignTriggerSetting(triggerConditionGroup, triggerActionGroup, 0, 10, 6000, 1);//unused, exp,money
			map.executeCampaign(player, 2, 350, 100, AIcontrol);//no of players, starting resources, basic income
			break;
		case 2:
			player[2].editUnit("armourGun",'a', 1, 1,1,1);
			player[2].editUnit("machine",'m', 2, 2,1,2);
			player[2].editUnit("bazooka",'k', 3, 4,2,3);
			setting = new int[]{510000, 612243,710000,810000,910161,/**/420000, 520000, 620000, 720000, /**/333242,430232,/**/240000,340000,440000,540000,643000,740000,/**/250000,351241,450000,750000,/**/360000,460000,560000,663223,760000,770000,/**/670000,770000,873000,/**/680242,780000,880000,/**/690000,791281,890000};
			triggerConditionGroup = new int[]{1001, 1001, 2171, 2135, 2135};
			triggerActionGroup[0]="message//\t\t\t\t\t[Open revolt]\n\n\n\t\tNearby Dominion outpost\n\t\tArzona valley\n\t\t3030Hrs";
			triggerActionGroup[1]="message//\t\tThanks to our crushing successful defence at widmoor, we have secured our base.\n\t\tThis is the time to open revolt.\n\n\t\tWe successfully sneaked into their outpost at Arzona valley,\n\t\tand hopefully for us, their are under asleep\n\t\tOur objective here is to take enemy base and strike remaining enemy forces.";
			triggerActionGroup[2]="message//\t\tShhh! try to minimize contact with enemy forces.  \n\t\tIt will only cause trouble if we commit unneccesary ussault.";
			triggerActionGroup[3]="message//\t\tThey realized that we took their base!\n\t\tLet's give them a full revenge of what they've done to us!\n\n\t\tObjective 1 : Defeat all enemy forces in Arzona valley";
			triggerActionGroup[4]="AIscript:CampaignEasy//2";
			AIcontrol = new boolean[]{false, false, true};
			map.editCampaignMap(setting, player);
			map.campaignTriggerSetting(triggerConditionGroup, triggerActionGroup, 0, 10, 8000, 1);
			map.executeCampaign(player, 2, 300, 0, AIcontrol);
			break;

		case 3:
			player[2].editUnit("armourGun",'a', 1, 1,1,1);
			player[2].editUnit("machine",'m', 2, 2,1,2);
			player[2].editUnit("bazooka",'k', 3, 4,2,3);
			setting = new int[]{510000, 612243,710000,810000,910161,/**/420000, 520000, 620000, 720000, /**/333242,430232,/**/240000,340000,440000,540000,643000,740000,/**/250000,351241,450000,750000,/**/360000,460000,560000,663223,760000,770000,/**/670000,770000,873000,/**/680242,780000,880000,/**/690000,791281,890000};
			triggerConditionGroup = new int[]{1001, 1001, 2171, 2135, 2135};
			triggerActionGroup[0]="message//\t\t\t\t\t[First Strike]\n\n\n\t\tNearby Dominion outpost\n\t\tArzona valley\n\t\t3030Hrs";
			triggerActionGroup[1]="message//\t\tThanks to our crushing successful defence at widmoor, we have secured our base.\n\t\tThis is the time to open revolt.\n\n\t\tWe successfully sneaked into their outpost at Arzona valley,\n\t\tand hopefully for us, their are under asleep\n\t\tOur objective here is to take enemy base and strike remaining enemy forces.";
			triggerActionGroup[2]="message//\t\tShhh! try to minimize contact with enemy forces.  \n\t\tUnnecessary contact will only cause trouble.";
			triggerActionGroup[3]="message//\t\tThey realized that we took their base!\n\t\tLet's give them a full revenge of what they've done to us!\n\n\t\tObjective 1 : Defeat all enemy forces in Arzona valley";
			triggerActionGroup[4]="AIscript:Custom//2";
			AIcontrol = new boolean[]{false, false, true};
			map.editCampaignMap(setting, player);
			map.campaignTriggerSetting(triggerConditionGroup, triggerActionGroup, 0, 10, 8000, 1);
			map.executeCampaign(player, 2, 300, 0, AIcontrol);
			break;

			/*int count=0;
			for (int i=5;i<=9 ;i++ )
				for (int j=1;j<=5 ; j++)
				{
					setting[count]=(i*10+j)*10000;
					count++;
				}*/

		case 101:
			player[2].editUnit("armourGun",'a', 1, 1,1,1);
			player[2].editUnit("machineGun",'m', 2, 2,1,2);
			player[2].editUnit("tank",'S', 3, 5,6,8);
			setting = new int[]{513000, 610000,/**/420000, 520000, 621100, 720000, /**/330000,433000,530000,630000,730000,830000,/**/240000,340000,440000,540000,643000,740000,840000,/**/150000, 252000,350000,453000,553000,653000,750000,850000,/**/260000,360000,460000,563000,660000,760000,860000,/**/370000,470000,570000,670000,773000,870000,/**/480000,581200,680000,780000,/**/590000,693000};
			triggerConditionGroup = new int[]{1001, 1001};
			triggerActionGroup[0]="message//\t\t\t\t\t[Custom match]\n\n\n\t\tCalm before the storm\n\t\tCornucopia";
			triggerActionGroup[1]="AIscript:Custom//2";
			AIcontrol = new boolean[]{false, false, true};
			map.editCampaignMap(setting, player);
			map.campaignTriggerSetting(triggerConditionGroup, triggerActionGroup, 0, 10, 8000, 0);
			map.executeCampaign(player, 2, 300, 0, AIcontrol);
			break;
		}



	}
}
/***
widmoor outpost
thanks to crushing victory at widmoor, we have advanced and set up new outpost called earthreach
welcome to starfall. Enemies are attacking at all directions. get an army together to fight them off as fast as you can!
(calm before the storm) You must secure the bridge to protect our newest outpost. you'll need large garrison to hold them off.
(Rush!) The mountain outpost has stirred up a hornet's nest. Supplies of gold and wood are on the way from northwood. defend the outpost at all cost before supplies arrive.
(From scratch) with Hammerton outpost in place, we can break the back of northern emnemies and drive them from their mountain home. our coffers are empty however, so you will have to make best use of resources you gather yourself.
(So much gold!) We need this outpost to stand until we builld up our war chest. As soon as we've secured enough gold, we can push further south. // amass a war chest of 7000 g
(Parched Earth) There is no way you can hold out with spare resources here. Hold out at all cost until reinforcements arrive.
(Hold out!) Through these tunnels is the dominion stronghold. They will do everything to stop us here. This wasteland will not support a large army. Still, you have to prevail.
(United they fall) Our sweat and struggle led us here. The final bastion of dominion. The remaining capitol forces will face us here, as well. Expect to face the full burnt of their combined forces.

No tech, but there is base upgrades (Up to level 3) : increase income level, max unit count.


������
����� �η��� ���� ����
���� �� ���ٸ�
����ȸ��
��� ����
 





***/